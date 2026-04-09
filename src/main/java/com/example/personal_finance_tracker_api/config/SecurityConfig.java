package com.example.personal_finance_tracker_api.config;

import com.example.personal_finance_tracker_api.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception
    {
       return configuration.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) throws Exception
    {

        http.csrf((csrf)->csrf.disable())
                .authorizeHttpRequests((auth)->auth

                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/").permitAll()

                        //Swagger and OpenAPI - exact paths only at start or end
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()


                        //SecurityFilterChain (Request-level authorization).akhane request URL + HTTP method onojayi access control aet kora hoic
                        // Auth APIs (Register/Login)
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()


                        // Users API → Admin only
                        .requestMatchers("/api/v1/users/**").hasRole("ADMIN")

                        //Best Practice ( j kno 1 jaigai likle e hbe controller a na likle controller clean thake)
                        // Transactions
                        .requestMatchers(HttpMethod.GET, "/api/v1/transactions/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/transactions").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/transactions/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/transactions/**").hasAnyRole("USER","ADMIN")
                        // Reports
                        .requestMatchers(HttpMethod.GET, "/api/v1/reports/**").hasAnyRole("USER", "ADMIN")





                        // Everything else
                        .anyRequest().authenticated()
                )
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();

    }
}

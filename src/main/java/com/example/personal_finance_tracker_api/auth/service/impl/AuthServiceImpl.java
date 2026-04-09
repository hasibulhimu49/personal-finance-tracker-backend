package com.example.personal_finance_tracker_api.auth.service.impl;

import com.example.personal_finance_tracker_api.auth.dto.request.LoginRequest;
import com.example.personal_finance_tracker_api.auth.dto.request.RegisterRequest;
import com.example.personal_finance_tracker_api.auth.dto.response.AuthResponse;
import com.example.personal_finance_tracker_api.auth.dto.response.RegisterResponse;
import com.example.personal_finance_tracker_api.auth.mapper.RegisterMapper;
import com.example.personal_finance_tracker_api.auth.service.AuthService;
import com.example.personal_finance_tracker_api.common.enums.Role;
import com.example.personal_finance_tracker_api.security.UserPrincipal;
import com.example.personal_finance_tracker_api.security.JwtService;
import com.example.personal_finance_tracker_api.user.entity.User;
import com.example.personal_finance_tracker_api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final RegisterMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    public RegisterResponse register(RegisterRequest request){

        User user=mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        User saved=repository.save(user);
        return mapper.toDto(saved);

    }

    public AuthResponse login(LoginRequest loginRequest) {

        User user = repository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(new UserPrincipal(user));


        return new AuthResponse("Login successful", token);
    }


    public void logout(){

    }
}

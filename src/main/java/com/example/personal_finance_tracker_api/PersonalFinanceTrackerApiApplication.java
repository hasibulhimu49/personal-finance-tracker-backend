package com.example.personal_finance_tracker_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PersonalFinanceTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceTrackerApiApplication.class, args);
	}

}

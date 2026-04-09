package com.example.personal_finance_tracker_api.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Personal Finance Tracker API");
        response.put("version", "1.0.0");
        response.put("status", "running");
        response.put("documentation", "/swagger-ui/index.html");
        response.put("apiDocs", "/v3/api-docs");
        response.put("health", "/actuator/health");
        response.put("github", "https://github.com/hasibulhimu49/personal-finance-tracker-backend");
        response.put("developer", "Mohammad Hasibul Hasan");
        response.put("email", "hasibulx2026@gmail.com");
        return response;
    }
}
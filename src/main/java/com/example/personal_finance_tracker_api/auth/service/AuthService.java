package com.example.personal_finance_tracker_api.auth.service;

import com.example.personal_finance_tracker_api.auth.dto.request.LoginRequest;
import com.example.personal_finance_tracker_api.auth.dto.request.RegisterRequest;
import com.example.personal_finance_tracker_api.auth.dto.response.AuthResponse;
import com.example.personal_finance_tracker_api.auth.dto.response.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AuthService {
    public RegisterResponse register(RegisterRequest request);
    public AuthResponse login(LoginRequest loginRequest);
    public void logout();
}

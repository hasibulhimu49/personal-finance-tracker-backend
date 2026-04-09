package com.example.personal_finance_tracker_api.auth.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String message;
    private String token;
}

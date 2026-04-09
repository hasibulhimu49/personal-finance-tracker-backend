package com.example.personal_finance_tracker_api.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String username;
    private String email;
}

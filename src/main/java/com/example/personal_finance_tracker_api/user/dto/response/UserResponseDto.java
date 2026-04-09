package com.example.personal_finance_tracker_api.user.dto.response;

import com.example.personal_finance_tracker_api.common.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    @NotNull(message = "Username Cannot be null")
    private String username;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

}

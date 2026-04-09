package com.example.personal_finance_tracker_api.user.dto.request;

import com.example.personal_finance_tracker_api.common.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequestDto {

    private Long id;

    @NotBlank(message = "Username cannot be empty")
    @Pattern(
            regexp = "^[a-zA-Z][a-zA-Z0-9]{2,19}$",
            message = "Username must start with a letter and be 3–20 characters long"
    )
    @Column(name="username", unique = true, nullable = false)
    private String username;
    private String password;
    private String email;
    private Role role;

}

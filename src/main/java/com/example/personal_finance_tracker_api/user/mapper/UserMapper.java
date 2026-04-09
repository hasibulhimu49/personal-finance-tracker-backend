package com.example.personal_finance_tracker_api.user.mapper;

import com.example.personal_finance_tracker_api.user.dto.request.UserCreateRequestDto;
import com.example.personal_finance_tracker_api.user.dto.response.UserResponseDto;
import com.example.personal_finance_tracker_api.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequestDto dto)
    {
        User user=new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return user ;

    }

    public UserResponseDto toDto(User user)
    {
        UserResponseDto responseDto=new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        responseDto.setRole(user.getRole());
        return responseDto;
    }
}

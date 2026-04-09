package com.example.personal_finance_tracker_api.user.service;

import com.example.personal_finance_tracker_api.user.dto.request.UserCreateRequestDto;
import com.example.personal_finance_tracker_api.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserResponseDto createUser(UserCreateRequestDto dto);
    public List<UserResponseDto> getAllUser();
    public UserResponseDto getUserById(Long id);
    public void deleteUser(Long id);

}

package com.example.personal_finance_tracker_api.user.service.impl;

import com.example.personal_finance_tracker_api.common.enums.Role;
import com.example.personal_finance_tracker_api.common.exception.ResourceNotFoundException;
import com.example.personal_finance_tracker_api.user.dto.request.UserCreateRequestDto;
import com.example.personal_finance_tracker_api.user.dto.response.UserResponseDto;
import com.example.personal_finance_tracker_api.user.entity.User;
import com.example.personal_finance_tracker_api.user.mapper.UserMapper;
import com.example.personal_finance_tracker_api.user.repository.UserRepository;
import com.example.personal_finance_tracker_api.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository repository;
    public UserMapper mapper;

    //Constructor injection
    UserServiceImpl(UserRepository repository,UserMapper mapper)
    {
        this.repository=repository;
        this.mapper=mapper;
    }


    //create User
    public UserResponseDto createUser(UserCreateRequestDto dto)
    {
        User user=mapper.toEntity(dto);
        user.setRole(Role.USER);
        User saved=repository.save(user);
        saved.getId();

        return mapper.toDto(saved);
    }



    //Get All User
    public List<UserResponseDto> getAllUser()
    {
        List<User> users=repository.findAll();
        return users.stream().map(user->mapper.toDto(user)).toList();
    }


    //Get User by id
    public UserResponseDto getUserById(Long id)
    {
        User user=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't find any user for id: "+id));
        return mapper.toDto(user);
    }


    public void deleteUser(Long id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Can't find any user for id: " + id);
        }
        repository.deleteById(id);
    }


}

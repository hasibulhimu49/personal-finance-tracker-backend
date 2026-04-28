package com.example.personal_finance_tracker_api.user.controller;

import com.example.personal_finance_tracker_api.user.dto.request.UserCreateRequestDto;
import com.example.personal_finance_tracker_api.user.dto.response.UserResponseDto;
import com.example.personal_finance_tracker_api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Tag(name = "User Management API", description = "APIs for managing users in the system")
@SecurityRequirement(name = "bearerAuth")
public class UserController {


    private final UserService service;



    //Post Users
    @Operation(summary = "Create user", description = "Here Only admin can create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created user"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserCreateRequestDto dto) {
        return service.createUser(dto);
    }





    //Get all Users
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<UserResponseDto> getAllUser() {
        return service.getAllUser();
    }





    //Get Users
    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }





    //Delete Users
    @Operation(summary = "Delete user", description = "Delete a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
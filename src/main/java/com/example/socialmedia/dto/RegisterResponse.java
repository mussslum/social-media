package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class RegisterResponse {

    private String status;
    private UserResponse userResponse;
}

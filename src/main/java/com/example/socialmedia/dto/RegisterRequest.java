package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;
    private String verifyCode;
}

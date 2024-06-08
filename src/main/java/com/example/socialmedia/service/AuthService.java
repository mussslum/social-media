package com.example.socialmedia.service;

import com.example.socialmedia.dto.RegisterRequest;
import com.example.socialmedia.dto.RegisterResponse;
import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthService {
    public String register(UserDto userDto);
    public RegisterResponse verifyAccount(RegisterRequest registerRequest);
    public User findByUsername(String username);
    public User getCurrentUser();
    public List<UserDto> getAllUserr();
    public User getById(int id);
}

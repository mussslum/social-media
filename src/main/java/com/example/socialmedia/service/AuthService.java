package com.example.socialmedia.service;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthService {
    public String register(UserDto userDto);
    public User findByUsername(String username);

    public User getCurrentUser();
    public List<UserDto> getAllUserr();
}

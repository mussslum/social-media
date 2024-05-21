package com.example.socialmedia.controller;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.TweetRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/users")
    public List<UserDto> users(){
        return authService.getAllUserr();
    }
    @PostMapping("/register")
    public String register (@RequestBody UserDto userDto){
        return authService.register(userDto);
    }
}

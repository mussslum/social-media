package com.example.socialmedia.service;

import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final AuthService authService;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<String> follow(int id) {
        User user = authService.getById(id);
        List<User> userList= new ArrayList<>();
        userList.add(user);
        if(user==null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = authService.getCurrentUser();
        if (currentUser.getFollowers().contains(user)){
            return new ResponseEntity<>("You already follow the given User",HttpStatus.FOUND);
        }
        currentUser.setFollowers(userList);
        userRepository.save(currentUser);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
}

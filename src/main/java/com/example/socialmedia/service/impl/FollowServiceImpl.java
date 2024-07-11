package com.example.socialmedia.service.impl;

import com.example.socialmedia.model.FollowRequest;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.FollowRequestRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.CommentService;
import com.example.socialmedia.service.CommonService;
import com.example.socialmedia.service.FollowService;
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
public class FollowServiceImpl implements FollowService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final FollowRequestRepository followRequestRepository;
    private final CommonService commonService;
    @Override
    public ResponseEntity<String> follow(int id) {
        User user = authService.getById(id);
        if(user==null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = authService.getCurrentUser();
        if(currentUser==user){
            return new ResponseEntity<>("You cannot follow yourself , it is absurd",HttpStatus.BAD_REQUEST);
        }
        if(user.getFollowers().contains(currentUser)){
            return new ResponseEntity<>("You already follow the given User",HttpStatus.FOUND);
        }
        if (user.getIsPrivate()){
            FollowRequest followRequest = new FollowRequest();
            followRequest.setSourceUser(currentUser);
            followRequest.setTargetUserId(user.getId());
            followRequestRepository.save(followRequest);
            return new ResponseEntity<>("Your follow request has been sent",HttpStatus.OK);
        }
        currentUser.getFollowers().add(user);
        userRepository.save(currentUser);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> unfollow(int id) {
        User user = authService.getById(id);
        if(user==null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = authService.getCurrentUser();
        if (!currentUser.getFollowers().contains(user)){
            return new ResponseEntity<>("You don't follow the given user",HttpStatus.EXPECTATION_FAILED);
        }
        currentUser.getFollowers().remove(user);
        userRepository.save(currentUser);
        return new ResponseEntity<>("You don't follow the given user anymore",HttpStatus.OK);
    }
}

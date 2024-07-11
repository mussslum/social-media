package com.example.socialmedia.service.impl;

import com.example.socialmedia.model.FollowRequest;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.FollowRequestRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.FollowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowRequestServiceImpl implements FollowRequestService {

    private final FollowRequestRepository followRequestRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Override
    public FollowRequest getById(int id) {
        Optional<FollowRequest> followRequest=followRequestRepository.findById(id);
        if(!followRequest.isPresent()){
            return null;
        }
        return followRequest.get();
    }

    @Override
    public ResponseEntity<String> accept(int requestId) {
        User currentUser = authService.getCurrentUser();
        FollowRequest followRequest = getById(requestId);
        if(followRequest==null){
            return new ResponseEntity<>("Not found Follow Request with given id", HttpStatus.NOT_FOUND);
        }
        if(currentUser.getId()!=followRequest.getTargetUserId()){
            return new ResponseEntity<>("This follow request is not your",HttpStatus.FORBIDDEN);
        }
        User user = authService.getById(followRequest.getSourceUser().getId());
        user.getFollowers().add(currentUser);
        userRepository.save(user);
        followRequestRepository.delete(followRequest);
        return new ResponseEntity<>("Follow Request has been accepted",HttpStatus.OK);
    }
}

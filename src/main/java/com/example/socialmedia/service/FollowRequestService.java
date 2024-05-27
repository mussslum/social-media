package com.example.socialmedia.service;

import com.example.socialmedia.model.FollowRequest;
import org.springframework.http.ResponseEntity;

public interface FollowRequestService {
    public FollowRequest getById(int id);
    public ResponseEntity<String> accept(int requestId);
}

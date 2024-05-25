package com.example.socialmedia.service;

import org.springframework.http.ResponseEntity;

public interface FollowService {
    public ResponseEntity<String> follow(int id);
    public ResponseEntity<String> unfollow(int id);
}

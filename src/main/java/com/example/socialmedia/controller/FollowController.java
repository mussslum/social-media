package com.example.socialmedia.controller;

import com.example.socialmedia.model.Comment;
import com.example.socialmedia.repository.CommentRepository;
import com.example.socialmedia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("follow")
public class FollowController {

    private final FollowService followService;
    private final CommentRepository commentRepository;

    @PostMapping("/add/{id}")
    public ResponseEntity<String> follow(@PathVariable int id){
        return followService.follow(id);
    }
    @PostMapping("/remove/{id}")
    public ResponseEntity<String> unfollow(@PathVariable int id){
        return followService.unfollow(id);
    }
}

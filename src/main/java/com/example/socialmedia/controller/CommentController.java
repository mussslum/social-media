package com.example.socialmedia.controller;

import com.example.socialmedia.dto.CommentResponse;
import com.example.socialmedia.dto.SaveCommentDto;
import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("save/{id}")
    public String saveComment(@PathVariable int id, @RequestBody SaveCommentDto saveCommentDto){
        return commentService.save(id, saveCommentDto);
    }
    @GetMapping("get-comments-by-tweet/{id}")
    public ResponseEntity<CommentResponse> getCommentsByTweet (@PathVariable int id){
        return commentService.getCommentsByTweetId(id);
    }
}

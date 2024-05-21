package com.example.socialmedia.controller;

import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.dto.CommentResponse;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("save/{id}")
    public String saveComment(@PathVariable int id, @RequestBody CommentDto commentDto){
        return commentService.save(id,commentDto);
    }
    @GetMapping("get-comments-by-tweet/{id}")
    public List<CommentResponse> getCommentsByTweet (@PathVariable int id){
        return commentService.getCommentsByTweetId(id);
    }
}

package com.example.socialmedia.service;

import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.dto.CommentResponse;
import com.example.socialmedia.dto.SaveCommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    public String save(int id , SaveCommentDto saveCommentDto);
    public ResponseEntity<CommentResponse> getCommentsByTweetId(int id);
}

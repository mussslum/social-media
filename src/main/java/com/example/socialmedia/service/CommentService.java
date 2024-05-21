package com.example.socialmedia.service;

import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    public String save(int id ,CommentDto commentDto);
    public List<CommentResponse> getCommentsByTweetId(int id);
}

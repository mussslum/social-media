package com.example.socialmedia.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentResponse {

    private String tweetContent;
    private List<CommentDto> commentDtos;
}

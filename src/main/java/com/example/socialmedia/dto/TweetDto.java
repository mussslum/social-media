package com.example.socialmedia.dto;

import lombok.Data;

import java.util.List;

@Data
public class TweetDto {

    private int id;
    private String content;
    private List<CommentDto> commentDtos;
}

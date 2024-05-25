package com.example.socialmedia.dto;

import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.model.User;
import lombok.Data;

@Data
public class SaveCommentDto {

    private String content;
    private User user;
    private Tweet tweet;
}

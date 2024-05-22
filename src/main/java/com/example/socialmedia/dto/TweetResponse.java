package com.example.socialmedia.dto;

import com.example.socialmedia.model.Tweet;
import lombok.Data;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;

@Data
public class TweetResponse {
    private String username;
    private List<TweetDto> tweetDtos;
}

package com.example.socialmedia.service;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.dto.TweetResponse;
import com.example.socialmedia.model.Tweet;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TweetService {
     public String save(TweetDto tweetDto);
     public List<TweetDto> getCurrentUserTweets();
     public ResponseEntity<TweetResponse> getTweetsByUserId(int id);
     public TweetDto getTweetDtoById(int id);
     public Tweet getTweetById(int id);
}

package com.example.socialmedia.controller;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.dto.TweetResponse;
import com.example.socialmedia.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tweet")
public class TweetController {

    private final TweetService tweetService;
    @PostMapping("/save")
    public String create (@RequestBody TweetDto tweetDto){
        return tweetService.save(tweetDto);
    }
    @GetMapping("/getAll")
    public List<TweetDto> getCurrentUserTweets(){
        return tweetService.getCurrentUserTweets();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<TweetResponse> getTweetsByUserId(@PathVariable int id){
        return tweetService.getTweetsByUserId(id);
    }
    @PostMapping("/like/{id}")
    public ResponseEntity<String> like(@PathVariable int id){
        return tweetService.like(id);
    }
    @GetMapping("/likes/{id}")
    public ResponseEntity<Integer> countOfLikes(@PathVariable int id){
        return tweetService.likes(id);
    }

}

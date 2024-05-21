package com.example.socialmedia.controller;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.repository.TweetRepository;
import com.example.socialmedia.service.TweetService;
import lombok.RequiredArgsConstructor;
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
    public List<TweetDto> getTweetsByUserId(@PathVariable int id){
        return tweetService.getTweetsByUserId(id);
    }

}

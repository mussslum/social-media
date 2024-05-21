package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.mapper.TweetMapper;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.TweetRepository;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.TweetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final AuthService authService;
    @Override
    public String save(TweetDto tweetDto) {
        Tweet tweet=tweetMapper.tweetDtoToTweet(tweetDto);
        tweet.setUser(authService.getCurrentUser());
        tweetRepository.save(tweet);
        return "OK";
    }

    @Override
    public List<TweetDto> getCurrentUserTweets() {
        int currenUserId = authService.getCurrentUser().getId();
        List<Tweet> tweets = tweetRepository.findByUserId(currenUserId);
        List<TweetDto> tweetDtos=tweetMapper.tweetsToTweetDtoList(tweets);
        return tweetDtos;
    }
    @Override
    public List<TweetDto> getTweetsByUserId(int id) {
        List<Tweet> tweets = tweetRepository.findByUserId(id);
        List<TweetDto> tweetDtos = tweetMapper.tweetsToTweetDtoList(tweets);
        return tweetDtos;
    }

    @Override
    public TweetDto getTweetDtoById(int id) {
        Optional<Tweet> tweet = tweetRepository.findById(id);
        TweetDto tweetDto;
        if(tweet.isPresent()){
            tweetDto = tweetMapper.tweetToTweetDto(tweet.get());
        }
        else{
            tweetDto=null;
        }
        return tweetDto;
    }

    @Override
    public Tweet getTweetById(int id) {
        Optional<Tweet> tweet = tweetRepository.findById(id);
        if(tweet.isPresent()){
            return tweet.get();
        }
        else{
            return null;
        }
    }


}

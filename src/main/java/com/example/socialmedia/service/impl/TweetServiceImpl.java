package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.dto.TweetResponse;
import com.example.socialmedia.mapper.CommentMapper;
import com.example.socialmedia.mapper.TweetMapper;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.TweetRepository;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.TweetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final AuthService authService;
    private final CommentMapper commentMapper;
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
    public ResponseEntity<TweetResponse> getTweetsByUserId(int id) {
        User currentUser =authService.getCurrentUser();
        TweetResponse tweetResponse = new TweetResponse();
        User user = authService.getById(id);
        if(user==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        List<Tweet> tweets = tweetRepository.findByUserId(id);
        tweetResponse.setTweetDtos(tweetMapper.tweetsToTweetDtoList(tweets));
        tweetResponse.setUsername(user.getUsername());
        if(user.getIsPrivate() && !currentUser.getFollowers().contains(user)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(tweetResponse,HttpStatus.OK);
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

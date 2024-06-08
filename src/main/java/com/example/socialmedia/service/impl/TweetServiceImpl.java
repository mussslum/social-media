package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.dto.TweetResponse;
import com.example.socialmedia.mapper.CommentMapper;
import com.example.socialmedia.mapper.TweetMapper;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.TweetRepository;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.CommonService;
import com.example.socialmedia.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommonService commonService;
    private final EmailSenderService emailSenderService;
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
        List<Tweet> tweets = tweetRepository.findFilteredTweetsByUserId(id);
        tweetResponse.setTweetDtos(tweetMapper.tweetsToTweetDtoList(tweets));
        tweetResponse.setUsername(user.getUsername());
        if(!commonService.canAccessProfile(id)){
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

    @Override
    public ResponseEntity<String> like(int id) {
        int count=0;
        User currentUser = authService.getCurrentUser();
        Optional<Tweet> tweet = tweetRepository.findById(id);
        if(!tweet.isPresent()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        User targetUser=authService.getById(tweet.get().getUser().getId());
        if(!commonService.canAccessProfile(targetUser.getId())){
            return new ResponseEntity<>("This is private account",HttpStatus.FORBIDDEN);
        }
        if (tweet.get().getUsersWhoLikeTweet().contains(currentUser)){
            return new ResponseEntity<>("You have already liked the tweet ",HttpStatus.OK);
        }
        tweet.get().getUsersWhoLikeTweet().add(currentUser);
        tweetRepository.save(tweet.get());
        return new ResponseEntity<>("You liked the tweet",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> likes(int id) {
        int count=0;
        Optional<Tweet> tweet=tweetRepository.findById(id);
        if (!tweet.isPresent()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        if(!commonService.canAccessProfile(tweet.get().getUser().getId())){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        count=tweet.get().getUsersWhoLikeTweet().size();
        return new ResponseEntity<>(count,HttpStatus.OK);
    }


}

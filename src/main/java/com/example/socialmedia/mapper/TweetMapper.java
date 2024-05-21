package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.model.Tweet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    Tweet tweetDtoToTweet (TweetDto tweetDto);
    List<TweetDto> tweetsToTweetDtoList(List<Tweet> tweets);
    public TweetDto tweetToTweetDto(Tweet tweet);
}

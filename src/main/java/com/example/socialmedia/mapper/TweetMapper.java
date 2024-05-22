package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.TweetDto;
import com.example.socialmedia.model.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    Tweet tweetDtoToTweet (TweetDto tweetDto);
    List<TweetDto> tweetsToTweetDtoList(List<Tweet> tweets);
    @Mapping(target = "commentResponses" ,source = "comments")
    public TweetDto tweetToTweetDto(Tweet tweet);
}

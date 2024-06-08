package com.example.socialmedia.repository;

import com.example.socialmedia.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Integer> {
    List<Tweet> findByUserId (int id);
    @Query(value = "SELECT * FROM social_media.tweet where user_id= ?1 and is_hidden=false ", nativeQuery = true)
    List<Tweet> findFilteredTweetsByUserId(int id);
}

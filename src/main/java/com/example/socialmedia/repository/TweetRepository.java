package com.example.socialmedia.repository;

import com.example.socialmedia.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Integer> {
    List<Tweet> findByUserId (int id);
}

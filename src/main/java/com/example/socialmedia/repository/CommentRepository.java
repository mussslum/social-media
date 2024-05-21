package com.example.socialmedia.repository;

import com.example.socialmedia.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    /*@Query(value = "SELECT * FROM social_media.comment where tweet_id = ?;" ,nativeQuery = true)*/
    public List<Comment> getByTweetId(int id);
}

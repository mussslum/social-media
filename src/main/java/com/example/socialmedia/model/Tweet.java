package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "tweet")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "users_tweetLikes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private List<User> usersWhoLikeTweet;

}

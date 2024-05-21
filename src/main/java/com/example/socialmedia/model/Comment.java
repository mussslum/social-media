package com.example.socialmedia.model;

import com.example.socialmedia.service.TweetService;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tweet tweet;


}

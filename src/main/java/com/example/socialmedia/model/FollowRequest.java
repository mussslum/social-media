package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FollowRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User sourceUser;

    @Column(name = "targetUser_id")
    private int targetUserId;

}

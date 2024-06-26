package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "user")
    private List<Tweet> tweets;

    @OneToMany(mappedBy ="user" )
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "user_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<User> followers;

    @OneToMany(mappedBy = "sourceUser")
    private List<FollowRequest> followRequests;

    private Boolean isPrivate=true;

    private String email;


}

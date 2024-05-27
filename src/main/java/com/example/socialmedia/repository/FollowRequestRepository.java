package com.example.socialmedia.repository;

import com.example.socialmedia.model.FollowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRequestRepository extends JpaRepository<FollowRequest,Integer> {
}

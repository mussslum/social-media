package com.example.socialmedia.repository;

import com.example.socialmedia.model.CacheUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacheUserRepository extends JpaRepository<CacheUser,Integer> {
    public CacheUser findByEmail(String email);
    public boolean existsByEmail(String email);
}

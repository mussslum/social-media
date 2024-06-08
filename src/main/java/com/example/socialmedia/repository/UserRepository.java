package com.example.socialmedia.repository;

import com.example.socialmedia.model.User;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUsername (String username);
    public boolean existsByUsername( String username);
    public boolean existsByEmail (String email);
}

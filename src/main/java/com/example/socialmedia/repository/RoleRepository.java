package com.example.socialmedia.repository;

import com.example.socialmedia.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.PublicKey;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Role findByName (String roleName);
}

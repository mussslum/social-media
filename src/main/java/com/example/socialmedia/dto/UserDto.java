package com.example.socialmedia.dto;

import com.example.socialmedia.model.Role;
import com.example.socialmedia.model.User;
import lombok.Data;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String confirmPassword;
    private List<Role> roles;

}

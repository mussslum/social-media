package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.enums.RoleName;
import com.example.socialmedia.mapper.UserMapper;
import com.example.socialmedia.model.Role;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.RoleRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public String register(UserDto userDto) {
        Role role =roleRepository.findByName(RoleName.USER.toString());
        List<Role> roles= new ArrayList<>();
        roles.add(role);
        User findUser = userRepository.findByUsername(userDto.getUsername());
        if(userDto.getPassword().equals(userDto.getConfirmPassword())){
            if(findUser==null){
                try {
                    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    userDto.setRoles(roles);
                    User user=userMapper.userDtoToUser(userDto);
                    userRepository.save(user);
                }catch (Exception e){
                    System.out.println("<<<<<<<<<<<"+e.getMessage()+">>>>>>>>>>>");
                    return "Problem with DataBase";
                }
                return "OK";
            }
            return "Username is taken";
        }
        return "Confirm password is wrong";
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User getCurrentUser() {
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        user=userRepository.findByUsername(username);
        return user;
    }

    @Override
    public List<UserDto> getAllUserr() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = userMapper.usersToUserDtoList(users);
        return userDtos;
    }

    @Override
    public User getById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

}

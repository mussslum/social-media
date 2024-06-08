package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.RegisterRequest;
import com.example.socialmedia.dto.RegisterResponse;
import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.dto.UserResponse;
import com.example.socialmedia.enums.RoleName;
import com.example.socialmedia.mapper.UserMapper;
import com.example.socialmedia.model.CacheUser;
import com.example.socialmedia.model.Role;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.CacheUserRepository;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final CacheUserRepository cacheUserRepository;
    private final EmailSenderService mailService;

    @Override
    public String register(UserDto userDto) {
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            return "Confirm password is wrong";
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            return  "User exist with the given email";
        }
        if(userRepository.existsByUsername(userDto.getUsername())){
            return "Username is taken";
        }
        String verifyCode= UUID.randomUUID().toString().substring(0,5);
        mailService.sendEmail(userDto.getEmail(),"Verify Code",verifyCode);
        CacheUser existCacheUser = cacheUserRepository.findByEmail(userDto.getEmail());
        if(cacheUserRepository.existsByEmail(userDto.getEmail())){
            existCacheUser.setVerifyCode(verifyCode);
            cacheUserRepository.save(existCacheUser);
            return "We sent verify code to your email , check your email";
        }
        CacheUser cacheUser=userMapper.userDtoToCacheUser(userDto);
        cacheUser.setVerifyCode(verifyCode);
        cacheUserRepository.save(cacheUser);
        return "We sent verify code to your email , check your email";
    }

    @Override
    public RegisterResponse verifyAccount(RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        CacheUser cacheUser =cacheUserRepository.findByEmail(registerRequest.getEmail());
        if(cacheUser==null){
            registerResponse.setStatus("We didn't send verify code for this email");
            return registerResponse;
        }
        if(!registerRequest.getVerifyCode().equals(cacheUser.getVerifyCode())){
            registerResponse.setStatus("Verify code is wrong");
            return registerResponse;
        }
        Role role =roleRepository.findByName(RoleName.ADMIN.toString());
        List<Role> roles= new ArrayList<>();
        roles.add(role);
        User user = userMapper.cacheUserToUser(cacheUser);
        user.setRoles(roles);
        String password= user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        UserResponse userResponse = UserResponse.builder()
                .username(user.getUsername())
                .password(password)
                .build();
        registerResponse.setStatus("User created successfully");
        registerResponse.setUserResponse(userResponse);
        cacheUserRepository.delete(cacheUser);
        return registerResponse;
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

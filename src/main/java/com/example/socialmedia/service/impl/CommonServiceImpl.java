package com.example.socialmedia.service.impl;

import com.example.socialmedia.model.User;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.CommentService;
import com.example.socialmedia.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final AuthService authService;
    @Override
    public boolean canAccessProfile(int userId){
        User currentUser =authService.getCurrentUser();
        User targetUser =authService.getById(userId);
        if(targetUser==null){
            return false;
        }
        if(targetUser.getIsPrivate() && !currentUser.getFollowers().contains(targetUser)){
            return false;
        }
        return true;
    }
}

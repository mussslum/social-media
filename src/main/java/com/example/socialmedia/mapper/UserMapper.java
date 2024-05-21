package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser (UserDto userDto);
    List<UserDto> usersToUserDtoList (List<User> users);
}

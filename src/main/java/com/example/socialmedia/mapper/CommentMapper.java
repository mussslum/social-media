package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.dto.CommentResponse;
import com.example.socialmedia.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    public Comment commentDtoToComment(CommentDto commentDto);
    List<CommentResponse> commentsToComentResponseList(List<Comment> comments);
}

package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.dto.SaveCommentDto;
import com.example.socialmedia.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    public Comment commentDtoToComment(SaveCommentDto saveCommentDto);
    List<CommentDto> commentsToComentResponseList(List<Comment> comments);
}

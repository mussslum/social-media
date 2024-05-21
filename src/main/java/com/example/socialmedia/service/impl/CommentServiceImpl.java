package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.dto.CommentResponse;
import com.example.socialmedia.mapper.CommentMapper;
import com.example.socialmedia.model.Comment;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.CommentRepository;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.CommentService;
import com.example.socialmedia.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final TweetService tweetService;
    @Override
    public String save(int id,CommentDto commentDto) {
        Comment comment ;
        try {
            User user = authService.getCurrentUser();
            Tweet tweet = tweetService.getTweetById(id);
            commentDto.setUser(user);
            commentDto.setTweet(tweet);
            comment=commentMapper.commentDtoToComment(commentDto);
            commentRepository.save(comment);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return "Problem with DataBase while saving";
        }
        return "OK";
    }

    @Override
    public List<CommentResponse> getCommentsByTweetId(int id) {
        List<CommentResponse> commentResponses;
        List<Comment> comments = commentRepository.getByTweetId(id);
        commentResponses=commentMapper.commentsToComentResponseList(comments);
        return commentResponses;
    }
}

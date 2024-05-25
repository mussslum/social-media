package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.dto.CommentResponse;
import com.example.socialmedia.dto.SaveCommentDto;
import com.example.socialmedia.mapper.CommentMapper;
import com.example.socialmedia.model.Comment;
import com.example.socialmedia.model.Tweet;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.CommentRepository;
import com.example.socialmedia.service.AuthService;
import com.example.socialmedia.service.CommentService;
import com.example.socialmedia.service.CommonService;
import com.example.socialmedia.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final TweetService tweetService;
    private final CommonService commonService;
    @Override
    public String save(int id, SaveCommentDto saveCommentDto) {
        Comment comment ;
        try {
            User currentUser = authService.getCurrentUser();
            Tweet tweet = tweetService.getTweetById(id);
            if (tweet==null){
                return "Not found tweet by given id";
            }
            if(!commonService.canAccessProfile(tweet.getUser().getId())){
                return "You cannot comment , because you don't follow the user";
            }
            saveCommentDto.setUser(currentUser);
            saveCommentDto.setTweet(tweet);
            comment=commentMapper.commentDtoToComment(saveCommentDto);
            commentRepository.save(comment);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return "Problem with DataBase while saving";
        }
        return "OK";
    }

    @Override
    public ResponseEntity<CommentResponse> getCommentsByTweetId(int id) {
        CommentResponse commentResponse= new CommentResponse();
        String tweetContent;
        List<CommentDto> commentDtos;
        Tweet tweet= tweetService.getTweetById(id);
        if(tweet==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(!commonService.canAccessProfile(tweet.getUser().getId())){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        List<Comment> comments=tweet.getComments();
        commentDtos =commentMapper.commentsToComentResponseList(comments);
        tweetContent=tweet.getContent();
        commentResponse.setCommentDtos(commentDtos);
        commentResponse.setTweetContent(tweetContent);
        return new ResponseEntity<>(commentResponse,HttpStatus.OK);
    }
}

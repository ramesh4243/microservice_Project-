package com.microservice.comment.controller;

import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.CommentDto;
import com.microservice.comment.service.impl.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
       CommentDto savedCommment = commentService.createComment(commentDto);
       return new ResponseEntity<>(savedCommment, HttpStatus.OK);
    }
    @GetMapping("{postId}")
    public List<Comment> getAllCommentsByPostId(@PathVariable String postId) {
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        return comments;
    }
}

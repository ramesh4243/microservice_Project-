package com.microservice.comment.service.impl;

import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);

    List<Comment> getAllCommentsByPostId(String postId);  //this abstract methdod converted into hql query.
}

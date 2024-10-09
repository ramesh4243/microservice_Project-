package com.microservice.comment.repository;

import com.microservice.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String > {
//    public List<Comment> getAllCommentsByPostId(String postId){
//        List<Comment> comments = commentRepository.findByPostId(postId);
//        return comments;
    List<Comment> findByPostId(String postId);
}

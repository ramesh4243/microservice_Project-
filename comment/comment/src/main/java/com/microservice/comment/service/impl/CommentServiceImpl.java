package com.microservice.comment.service.impl;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.CommentDto;

import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Autowired
    private RestTemplateConfig restTemplate;

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment  = mapToEntity(commentDto);
        //microservice interecting with post project by url.
       Post post = restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/posts/" + comment.getPostId(), Post.class);
       System.out.println("result " + post);
        if(post != null){
            String commentId = UUID.randomUUID().toString();  //id given manually so, it will used.
            comment.setId(commentId);
            Comment savedComment = commentRepository.save(comment);
            CommentDto dto =  mapToDTO(savedComment) ;
            return dto;
        }else{
            return null;
        }
    }

    private CommentDto mapToDTO(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        dto.setPostId(comment.getPostId());
        return dto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPostId(commentDto.getPostId());
        return comment;
    }
    @Override
    public List<Comment> getAllCommentsByPostId(String postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }

}

package com.microservice.comment.payload;

import lombok.Data;

@Data
public class CommentDto {
    private String id;
    private String name;
    private String email;
    private String body;
    private String postId;
}

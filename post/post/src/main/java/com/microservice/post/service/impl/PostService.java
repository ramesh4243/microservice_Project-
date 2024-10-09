package com.microservice.post.service.impl;

import com.microservice.post.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);
    public List<PostDto> getAllPosts();
    public PostDto getPostById(String id);
    public PostDto updatePost(String id, PostDto postDto);
    public void deletePost(String id);

   public PostDto getPostWithComments(String postId);
}

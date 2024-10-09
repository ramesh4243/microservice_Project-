package com.microservice.post.controller;

import com.microservice.post.payload.PostDto;
import com.microservice.post.service.impl.PostService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
//http:localhost:8081/api/posts/
    public PostController(PostService postService) {
        this.postService = postService;
    }
    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
        PostDto savedPostDto = postService.createPost(postDto);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }
        @GetMapping("/post")
        public ResponseEntity<List<PostDto>> getAllPosts() {
            List<PostDto> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }

        // Get a single Post by ID
        @GetMapping("/{id}")
        public ResponseEntity<PostDto> getPostById(@PathVariable("id") String postId) {
            PostDto postDto = postService.getPostById(postId);
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        }

        // Update an existing Post by ID
        @PutMapping("/update")
        public ResponseEntity<PostDto> updatePost(@PathVariable String id, @RequestBody PostDto postDto) {
            PostDto updatedPost = postService.updatePost(id, postDto);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        }

        // Delete a Post by ID
        @DeleteMapping("/delete")
        public ResponseEntity<Void> deletePost(@PathVariable String postId) {
            postService.deletePost(postId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //http://localhost:8081/api/posts/{postId}/comments
        @GetMapping("/{postId}/comments")
        @CircuitBreaker(name = "commentBreaker", fallbackMethod = "commentFallback")  //fallback id service down it willcall and tell services down.
        public ResponseEntity<PostDto> getPostWithComments(@PathVariable String postId){
        PostDto postDto = postService.getPostWithComments(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
        }
        public ResponseEntity<PostDto> commentFallback(String postId, Exception ex){
        System.out.println("Fall back is executed because service is down:"+ ex.getMessage());

        ex.printStackTrace();
        PostDto dto = new PostDto();
        dto.setPostId("1234");
        dto.setTitle("Service Down");
        dto.setContent("Service Down");
        dto.setDescription("Service Down");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

        }
    }



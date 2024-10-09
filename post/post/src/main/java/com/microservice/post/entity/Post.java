package com.microservice.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String postId;
    private String title;
    private String description;
    private String content;

}

package com.microservice.post.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String id, String s) {
        super(String.format("%s not found with id: %s", resource, id));
    }
}

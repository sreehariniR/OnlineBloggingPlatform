package com.blog.postservice.controller;

import com.blog.postservice.dto.CreatePostRequest;
import com.blog.postservice.dto.PostResponse;
import com.blog.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostResponse createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }

    @GetMapping("/{postId}")
    public PostResponse getPostById(@PathVariable String postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/author/{authorId}")
    public List<PostResponse> getPostsByAuthor(@PathVariable String authorId) {
        return postService.getPostsByAuthor(authorId);
    }

    @GetMapping("/published")
    public List<PostResponse> getPublishedPosts() {
        return postService.getPublishedPosts();
    }
}
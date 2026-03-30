package com.blog.postservice.service;

import com.blog.postservice.dto.CreatePostRequest;
import com.blog.postservice.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostResponse createPost(CreatePostRequest request);

    PostResponse getPostById(String postId);

    List<PostResponse> getPostsByAuthor(String authorId);

    List<PostResponse> getPublishedPosts();
}
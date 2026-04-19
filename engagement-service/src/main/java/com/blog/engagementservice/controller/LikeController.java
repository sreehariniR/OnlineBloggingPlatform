package com.blog.engagementservice.controller;

import com.blog.engagementservice.dto.CreateLikeRequest;
import com.blog.engagementservice.dto.LikeResponse;
import com.blog.engagementservice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public LikeResponse likePost(@RequestBody CreateLikeRequest request) {
        return likeService.likePost(request);
    }

    @DeleteMapping
    public void unlikePost(@RequestParam String userId, @RequestParam String postId) {
        likeService.unlikePost(userId, postId);
    }

    @GetMapping("/check")
    public boolean isLiked(@RequestParam String userId, @RequestParam String postId) {
        return likeService.isPostLiked(userId, postId);
    }
}
package com.blog.socialgraphservice.controller;

import com.blog.socialgraphservice.dto.FollowRequest;
import com.blog.socialgraphservice.node.UserNode;
import com.blog.socialgraphservice.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/social")
public class FollowController {

    private final FollowService followService;
    FollowController(FollowService followService){
        this.followService=followService;
    }

    @PostMapping("/follow")
    public void follow(@RequestBody FollowRequest request) {
        followService.follow(request);
    }

    @PostMapping("/unfollow")
    public void unfollow(@RequestBody FollowRequest request) {
        followService.unfollow(request);
    }

    @GetMapping("/followers/{userId}")
    public List<UserNode> followers(@PathVariable String userId) {
        return followService.getFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    public List<UserNode> following(@PathVariable String userId) {
        return followService.getFollowing(userId);
    }
}

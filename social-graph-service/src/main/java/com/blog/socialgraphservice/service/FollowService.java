package com.blog.socialgraphservice.service;
import com.blog.socialgraphservice.dto.FollowRequest;
import com.blog.socialgraphservice.node.UserNode;
import com.blog.socialgraphservice.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public void follow(FollowRequest request) {

        followRepository.followUser(
                request.getFollowerId(),
                request.getFollowingId()
        );
    }

    public void unfollow(FollowRequest request) {

        followRepository.unfollowUser(
                request.getFollowerId(),
                request.getFollowingId()
        );
    }

    public List<UserNode> getFollowers(String userId) {
        return followRepository.getFollowers(userId);
    }

    public List<UserNode> getFollowing(String userId) {
        return followRepository.getFollowing(userId);
    }
}
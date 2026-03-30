package com.blog.postservice.service;

import com.blog.postservice.dto.CreatePostRequest;
import com.blog.postservice.dto.PostResponse;
import com.blog.postservice.model.Post;
import com.blog.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostResponse createPost(CreatePostRequest request) {
        Instant now = Instant.now();

        Post post = Post.builder()
                .authorId(request.getAuthorId())
                .title(request.getTitle())
                .content(request.getContent())
                .excerpt(request.getExcerpt())
                .media(request.getMedia())
                .tags(request.getTags())
                .categoryName(request.getCategoryName())
                .coverImageURL(request.getCoverImageURL())
                .likesCount(0)
                .viewsCount(0)
                .sharesCount(0)
                .commentsCount(0)
                .commentsPreview(new ArrayList<>())
                .published(request.isPublished())
                .deleted(false)
                .createdDate(now)
                .updatedDate(now)
                .publishedDate(request.isPublished() ? now : null)
                .build();

        Post saved = postRepository.save(post);
        return mapToResponse(saved);
    }

    @Override
    public PostResponse getPostById(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return mapToResponse(post);
    }

    @Override
    public List<PostResponse> getPostsByAuthor(String authorId) {
        return postRepository.findByAuthorIdAndDeletedFalseOrderByCreatedDateDesc(authorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PostResponse> getPublishedPosts() {
        return postRepository.findByPublishedTrueAndDeletedFalseOrderByPublishedDateDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PostResponse mapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .authorId(post.getAuthorId())
                .title(post.getTitle())
                .content(post.getContent())
                .excerpt(post.getExcerpt())
                .media(post.getMedia())
                .tags(post.getTags())
                .categoryName(post.getCategoryName())
                .coverImageURL(post.getCoverImageURL())
                .likesCount(post.getLikesCount())
                .viewsCount(post.getViewsCount())
                .sharesCount(post.getSharesCount())
                .commentsCount(post.getCommentsCount())
                .commentsPreview(post.getCommentsPreview())
                .published(post.isPublished())
                .deleted(post.isDeleted())
                .createdDate(post.getCreatedDate())
                .updatedDate(post.getUpdatedDate())
                .publishedDate(post.getPublishedDate())
                .build();
    }
}
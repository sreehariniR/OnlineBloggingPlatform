package com.blog.postservice.repository;

import com.blog.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByAuthorIdAndDeletedFalseOrderByCreatedDateDesc(String authorId);

    List<Post> findByPublishedTrueAndDeletedFalseOrderByPublishedDateDesc();
}
package com.blog.engagementservice.controller;

import com.blog.engagementservice.dto.BookmarkResponse;
import com.blog.engagementservice.dto.CreateBookmarkRequest;
import com.blog.engagementservice.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public BookmarkResponse bookmarkPost(@RequestBody CreateBookmarkRequest request) {
        return bookmarkService.bookmarkPost(request);
    }

    @DeleteMapping
    public void removeBookmark(@RequestParam String userId, @RequestParam String postId) {
        bookmarkService.removeBookmark(userId, postId);
    }

    @GetMapping("/check")
    public boolean isBookmarked(@RequestParam String userId, @RequestParam String postId) {
        return bookmarkService.isPostBookmarked(userId, postId);
    }

    @GetMapping("/user/{userId}")
    public List<BookmarkResponse> getBookmarksByUser(@PathVariable String userId) {
        return bookmarkService.getBookmarksByUser(userId);
    }
}
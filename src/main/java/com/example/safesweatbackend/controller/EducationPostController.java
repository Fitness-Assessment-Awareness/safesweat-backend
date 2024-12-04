package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.model.dto.*;
import com.example.safesweatbackend.service.EducationPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/education-post")
public record EducationPostController(EducationPostService educationPostService) {

    @GetMapping("/list")
    public ResponseEntity<List<EducationPostDto>> getEducationPosts() {
        return ResponseEntity.ok(
            educationPostService.getAll()
        );
    }

    @GetMapping("/list-summary/bookmark/{userId}")
    public ResponseEntity<List<EducationPostSummaryDto>> getBookmarkPostSummaries(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(
                educationPostService.getAllBookmarkSummaries(userId)
        );
    }

    @GetMapping("/list-summary")
    public ResponseEntity<List<EducationPostSummaryDto>> getEducationPostSummaries() {
        return ResponseEntity.ok(
            educationPostService.getAllSummaries()
        );
    }

    @GetMapping("/list-summary/recommended/{userId}")
    public ResponseEntity<List<EducationPostSummaryDto>> getRecommendedEducationPosts(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(
                educationPostService.getRecommended(userId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationPostDto> getEducationPost(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                educationPostService.get(id)
        );
    }

    @PostMapping
    public ResponseEntity<EducationPostDto> createEducationPost(@RequestBody EducationPostDto educationPostDto) {
        return ResponseEntity.ok(
                educationPostService.create(educationPostDto)
        );
    }

    @PatchMapping
    public ResponseEntity<EducationPostDto> updateEducationPost(@RequestBody EducationPostDto educationPostDto) {
        return ResponseEntity.ok(
                educationPostService.update(educationPostDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducationPost(@PathVariable("id") UUID id) {
        educationPostService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<EducationPostCategoryDto>> getEducationPostCategories() {
        return ResponseEntity.ok(
                educationPostService.getAllCategories()
        );
    }

    @PostMapping("/like")
    public ResponseEntity<EducationPostLikeDto> likePost(@RequestBody EducationPostLikeDto educationPostLikeDto) {
        return ResponseEntity.ok(
                educationPostService.like(educationPostLikeDto)
        );
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> removeLikePost(@RequestBody EducationPostLikeDto educationPostLikeDto) {
        educationPostService.deleteLike(educationPostLikeDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/likes/{userId}")
    public ResponseEntity<Void> removeAllLikes(@PathVariable("userId") UUID userId) {
        educationPostService.deleteUserLikes(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/bookmark")
    public ResponseEntity<EducationPostBookmarkDto> bookmarkPost(@RequestBody EducationPostBookmarkDto educationPostBookmarkDto) {
        return ResponseEntity.ok(
                educationPostService.bookmark(educationPostBookmarkDto)
        );
    }

    @PostMapping("/bookmark-remove")
    public ResponseEntity<Void> removeBookmarkPost(@RequestBody EducationPostBookmarkDto educationPostBookmarkDto) {
        educationPostService.deleteBookmark(educationPostBookmarkDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/bookmark/{userId}")
    public ResponseEntity<Void> removeAllBookmarks(@PathVariable("userId") UUID userId) {
        educationPostService.deleteUserBookmarks(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

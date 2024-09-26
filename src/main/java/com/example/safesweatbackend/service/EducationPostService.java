package com.example.safesweatbackend.service;

import com.example.safesweatbackend.model.dto.*;

import java.util.List;
import java.util.UUID;

public interface EducationPostService {

    EducationPostDto create(EducationPostDto educationPostDTO);

    EducationPostDto get(UUID id);

    List<EducationPostDto> getAll();

    EducationPostDto update(EducationPostDto educationPostDTO);

    void delete(UUID id);

    List<EducationPostCategoryDto> getAllCategories();

    EducationPostLikeDto like(EducationPostLikeDto educationPostLikeDto);

    void deleteLike(EducationPostLikeDto educationPostLikeDto);

    void deleteUserLikes(UUID userId);

    List<EducationPostSummaryDto> getAllSummaries();

    EducationPostBookmarkDto bookmark(EducationPostBookmarkDto educationPostBookmarkDto);

    void deleteBookmark(EducationPostBookmarkDto educationPostBookmarkDto);

    void deleteUserBookmarks(UUID userId);
}

package com.example.safesweatbackend.service;

import com.example.safesweatbackend.model.dto.EducationPostCategoryDto;
import com.example.safesweatbackend.model.dto.EducationPostDto;
import com.example.safesweatbackend.model.dto.EducationPostLikeDto;
import com.example.safesweatbackend.model.dto.EducationPostSummaryDto;

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
}

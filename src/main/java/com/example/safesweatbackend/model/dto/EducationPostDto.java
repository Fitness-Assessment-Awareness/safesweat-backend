package com.example.safesweatbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationPostDto {

    private UUID postId;

    private String titleEn;

    private String titleMs;

    private String contentEn;

    private String contentMs;

    private UUID categoryId;

    private String imageUrl;

    private Date createdDate;

    private String createdBy;

    private Date lastUpdatedDate;

    private String lastUpdatedBy;

    private EducationPostCategoryDto categoryDto;

    private List<EducationPostLikeDto> educationPostLikeDtos;

    private List<EducationPostBookmarkDto> educationPostBookmarkDtos;
}

package com.example.safesweatbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationPostSummaryDto {

    private UUID postId;

    private String titleEn;

    private String titleMs;

    private String imageUrl;

    private UUID categoryId;

    private Long likeCount;
}

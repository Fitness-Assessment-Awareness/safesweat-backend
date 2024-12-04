package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.dto.EducationPostSummaryDto;
import com.example.safesweatbackend.model.entity.EducationPost;
import com.example.safesweatbackend.model.entity.EducationPostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EducationPostRepo extends JpaRepository<EducationPost, UUID> {

    @Query("SELECT c FROM education_post_category c WHERE c.categoryId = :categoryId")
    EducationPostCategory findCategoryById(UUID categoryId);

    @Query("SELECT c FROM education_post_category c")
    List<EducationPostCategory> findAllCategories();

    @Query("SELECT new com.example.safesweatbackend.model.dto.EducationPostSummaryDto(p.postId, p.titleEn, p.titleMs, p.imageUrl, p.categoryId, COUNT(l.id.postId)) FROM education_post p LEFT JOIN education_post_like l ON p.postId = l.id.postId GROUP BY p.postId")
    List<EducationPostSummaryDto> findAllSummaries();

    @Query("SELECT new com.example.safesweatbackend.model.dto.EducationPostSummaryDto(p.postId, p.titleEn, p.titleMs, p.imageUrl, p.categoryId, COUNT(l.id.postId)) FROM education_post_bookmark b " +
            "INNER JOIN education_post p ON p.postId = b.id.postId " +
            "LEFT JOIN education_post_like l ON p.postId = l.id.postId " +
            "WHERE b.id.userId = :userId GROUP BY p.postId")
    List<EducationPostSummaryDto> findAllBookmarkSummaries(UUID userId);

    @Query("SELECT new com.example.safesweatbackend.model.dto.EducationPostSummaryDto(p.postId, p.titleEn, p.titleMs, p.imageUrl, p.categoryId, COUNT(l.id.postId)) FROM education_post p " +
            "LEFT JOIN education_post_like l ON p.postId = l.id.postId " +
            "WHERE p.categoryId = :categoryId GROUP BY p.postId ORDER BY COUNT(p.postId) DESC")
    List<EducationPostSummaryDto> findPostsByCategoryIdOrderByLikesDesc(UUID categoryId);
}

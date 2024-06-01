package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.EducationPost;
import com.example.safesweatbackend.model.entity.EducationPostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EducationPostRepo extends JpaRepository<EducationPost, UUID> {

    @Query("SELECT c FROM education_post_category c WHERE c.categoryId = :categoryId")
    EducationPostCategory findCategoryById(UUID categoryId);
}

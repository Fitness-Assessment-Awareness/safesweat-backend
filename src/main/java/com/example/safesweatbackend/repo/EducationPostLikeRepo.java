package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.EducationPostLike;
import com.example.safesweatbackend.model.entity.EducationPostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EducationPostLikeRepo extends JpaRepository<EducationPostLike, EducationPostLikeId> {
    @Modifying
    @Query("DELETE FROM education_post_like l WHERE l.id.userId = :userId")
    void deleteLikesByUserId(UUID userId);
}

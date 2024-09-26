package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.EducationPostBookmark;
import com.example.safesweatbackend.model.entity.EducationPostBookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EducationPostBookmarkRepo extends JpaRepository<EducationPostBookmark, EducationPostBookmarkId> {
    @Modifying
    @Query("DELETE FROM education_post_bookmark l WHERE l.id.userId = :userId")
    void deleteUserBookmarks(UUID userId);
}

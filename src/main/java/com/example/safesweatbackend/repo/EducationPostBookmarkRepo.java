package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.EducationPostBookmark;
import com.example.safesweatbackend.model.entity.EducationPostBookmarkId;
import com.example.safesweatbackend.model.entity.EducationPostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EducationPostBookmarkRepo extends JpaRepository<EducationPostBookmark, EducationPostBookmarkId> {
    @Modifying
    @Query("DELETE FROM education_post_bookmark l WHERE l.id.userId = :userId")
    void deleteUserBookmarks(UUID userId);

    @Query("SELECT c FROM education_post_category c INNER JOIN education_post p ON p.categoryId = c.categoryId " +
            "INNER JOIN education_post_bookmark b ON p.postId = b.id.postId " +
            "WHERE b.id.userId = :userId GROUP BY c.categoryId ORDER BY COUNT(c.categoryId) DESC LIMIT 1")
    EducationPostCategory findUserFavouriteCategory(UUID userId);

    @Query("SELECT b.id.postId FROM education_post_bookmark b WHERE b.id.userId = :userId")
    List<UUID> findAllBookmarkedPostsByUserId(UUID userId);
}

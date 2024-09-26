package com.example.safesweatbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "education_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationPost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "title_ms")
    private String titleMs;

    @Column(name = "content_en")
    private String contentEn;

    @Column(name = "content_ms")
    private String contentMs;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private EducationPostCategory category;

    @OneToMany(mappedBy = "educationPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EducationPostLike> educationPostLikes;

    @OneToMany(mappedBy = "educationPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EducationPostBookmark> educationPostBookmarks;
}

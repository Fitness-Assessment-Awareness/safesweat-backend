package com.example.safesweatbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "education_post_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationPostLike {

    @EmbeddedId
    private EducationPostLikeId id;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private EducationPost educationPost;
}

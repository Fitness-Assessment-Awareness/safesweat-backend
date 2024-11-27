package com.example.safesweatbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "education_post_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationPostCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_ms", nullable = false)
    private String nameMs;

    @OneToMany(mappedBy = "category")
    List<EducationPost> educationPosts;
}

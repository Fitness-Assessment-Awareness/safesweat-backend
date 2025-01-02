package com.example.safesweatbackend.model.entity;

import com.example.safesweatbackend.model.type.DifficultyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "workout_plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID planId;

    @Column(name = "title_en", nullable = false)
    private String titleEn;

    @Column(name = "title_ms", nullable = false)
    private String titleMs;

    @Column(name = "estimated_time_minute", nullable = false)
    private Short estimatedTimeMinute;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "introduction_en")
    private String introductionEn;

    @Column(name = "introduction_ms")
    private String introductionMs;

    @Column(name = "difficulty")
    @Enumerated(EnumType.STRING)
    private DifficultyType difficulty;

    @Column(name = "focus_area_id")
    private UUID focusAreaId;

    @ManyToOne
    @JoinColumn(name = "focus_area_id", insertable = false, updatable = false)
    private FocusArea focusArea;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id.exerciseId ASC")
    private List<WorkoutPlanExercise> workoutPlanExercises;
}

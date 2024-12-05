package com.example.safesweatbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "workout_plan_exercise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutPlanExercise {

    @EmbeddedId
    private WorkoutPlanExerciseId id;

    @Column(name = "estimated_time_second")
    private Short estimatedTimeSecond;

    @Column(name = "rep_count")
    private Short repCount;

    @ManyToOne
    @MapsId("planId")
    @JoinColumn(name = "plan_id")
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
}

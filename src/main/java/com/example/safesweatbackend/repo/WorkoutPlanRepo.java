package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.Exercise;
import com.example.safesweatbackend.model.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan, UUID> {

    @Query("SELECT e FROM exercise e WHERE e.exerciseId = :exerciseId")
    Exercise findExerciseById(UUID exerciseId);

}

package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.WorkoutPlanExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WorkoutPlanExerciseRepo extends JpaRepository<WorkoutPlanExercise, UUID> {

    @Modifying
    @Query("DELETE FROM workout_plan_exercise wpe WHERE wpe.id.exerciseId = :exerciseId")
    void deleteByExerciseId(UUID exerciseId);
}

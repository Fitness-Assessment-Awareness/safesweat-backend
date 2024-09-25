package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.Exercise;
import com.example.safesweatbackend.model.entity.WorkoutPlanExercise;
import com.example.safesweatbackend.model.entity.WorkoutPlanExerciseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WorkoutPlanExerciseRepo extends JpaRepository<WorkoutPlanExercise, WorkoutPlanExerciseId> {

    @Query("SELECT e FROM exercise e")
    List<Exercise> findAllExercises();

    @Query("SELECT e FROM exercise e WHERE e.exerciseId = :exerciseId")
    Exercise findExerciseById(UUID exerciseId);

    @Modifying
    @Query("DELETE FROM workout_plan_exercise wpe WHERE wpe.id.exerciseId = :exerciseId")
    void deleteByExerciseId(UUID exerciseId);
}

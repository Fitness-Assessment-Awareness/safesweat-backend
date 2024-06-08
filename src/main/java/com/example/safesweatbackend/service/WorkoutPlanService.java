package com.example.safesweatbackend.service;

import com.example.safesweatbackend.model.dto.ExerciseDto;
import com.example.safesweatbackend.model.dto.WorkoutPlanDto;

import java.util.List;
import java.util.UUID;

public interface WorkoutPlanService {

    WorkoutPlanDto create(WorkoutPlanDto workoutPlanDto);

    WorkoutPlanDto get(UUID id);

    List<WorkoutPlanDto> getAll();

    WorkoutPlanDto update(WorkoutPlanDto workoutPlanDto);

    void delete(UUID id);

    List<ExerciseDto> getAllExercises();
}

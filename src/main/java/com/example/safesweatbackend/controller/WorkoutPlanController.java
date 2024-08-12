package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.model.dto.ExerciseDto;
import com.example.safesweatbackend.model.dto.WorkoutPlanDto;
import com.example.safesweatbackend.service.WorkoutPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workout-plan")
public record WorkoutPlanController(WorkoutPlanService workoutPlanService) {

    @GetMapping("/list")
    public ResponseEntity<List<WorkoutPlanDto>> getWorkoutPlans() {
        return ResponseEntity.ok(
                workoutPlanService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanDto> getWorkoutPlan(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                workoutPlanService.get(id)
        );
    }

    @PostMapping
    public ResponseEntity<WorkoutPlanDto> createWorkoutPlan(@RequestBody WorkoutPlanDto workoutPlanDto) {
        return ResponseEntity.ok(
                workoutPlanService.create(workoutPlanDto)
        );
    }

    @PatchMapping
    public ResponseEntity<WorkoutPlanDto> updateWorkoutPlan(@RequestBody WorkoutPlanDto workoutPlanDto) {
        return ResponseEntity.ok(
                workoutPlanService.update(workoutPlanDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable("id") UUID id) {
        workoutPlanService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDto>> getExercises() {
        return ResponseEntity.ok(
                workoutPlanService.getAllExercises()
        );
    }
}

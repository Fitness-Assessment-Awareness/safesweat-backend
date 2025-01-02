package com.example.safesweatbackend.service;

import com.example.safesweatbackend.mapper.ExerciseMapper;
import com.example.safesweatbackend.mapper.WorkoutPlanMapper;
import com.example.safesweatbackend.model.dto.ExerciseDto;
import com.example.safesweatbackend.model.dto.WorkoutPlanDto;
import com.example.safesweatbackend.model.entity.Exercise;
import com.example.safesweatbackend.model.entity.WorkoutPlan;
import com.example.safesweatbackend.model.entity.WorkoutPlanExercise;
import com.example.safesweatbackend.repo.WorkoutPlanExerciseRepo;
import com.example.safesweatbackend.repo.WorkoutPlanRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepo workoutPlanRepo;

    private final WorkoutPlanExerciseRepo workoutPlanExerciseRepo;

    private final WorkoutPlanMapper planMapper;

    private final ExerciseMapper exerciseMapper;

    @Override
    @Transactional
    public WorkoutPlanDto create(WorkoutPlanDto workoutPlanDto) {
        if (!ObjectUtils.isEmpty(workoutPlanDto.getPlanId())) {
            throw new IllegalArgumentException("Plan ID must be null");
        }
        WorkoutPlan workoutPlan = planMapper.workoutPlanDtoToWorkoutPlan(workoutPlanDto);
        List<WorkoutPlanExercise> workoutPlanExercises = workoutPlan.getWorkoutPlanExercises();
        if (workoutPlan.getPlanId() == null) {
            workoutPlan.setFocusAreaId(UUID.fromString("5e23ea41-6351-48e6-91cd-ed83197f2a29"));
        }
        WorkoutPlan workoutPlanCreated = workoutPlanRepo.save(workoutPlan);
        if (!CollectionUtils.isEmpty(workoutPlanExercises)) {
            assignExerciseAndPlanToWorkoutPlanExercises(workoutPlanExercises, workoutPlanCreated);
            workoutPlanExerciseRepo.saveAll(workoutPlanExercises);
        }
        return planMapper.workoutPlanToDto(workoutPlanCreated);
    }

    @Override
    public WorkoutPlanDto get(UUID id) {
        WorkoutPlan workoutPlan = workoutPlanRepo.findById(id).get();
        return planMapper.workoutPlanToDto(workoutPlan);
    }

    @Override
    public List<WorkoutPlanDto> getAll() {
        List<WorkoutPlan> workoutPlans = workoutPlanRepo.findAll();
        return planMapper.workoutPlansToDtos(workoutPlans);
    }

    @Override
    @Transactional
    public WorkoutPlanDto update(WorkoutPlanDto workoutPlanDto) {
        UUID planId = workoutPlanDto.getPlanId();
        WorkoutPlan workoutPlan = workoutPlanRepo.findById(planId).get();
        Set<UUID> exerciseIdsBeforeUpdate = new HashSet<>();
        if (!CollectionUtils.isEmpty(workoutPlanDto.getWorkoutPlanExerciseDtos())) {
            exerciseIdsBeforeUpdate = workoutPlan.getWorkoutPlanExercises().stream()
                    .map(workoutPlanExercise -> workoutPlanExercise.getId().getExerciseId())
                    .collect(Collectors.toSet());
        }
        planMapper.updateWorkoutPlanFromDto(workoutPlanDto, workoutPlan);
        if (!CollectionUtils.isEmpty(workoutPlanDto.getWorkoutPlanExerciseDtos())) {
            assignExerciseAndPlanToWorkoutPlanExercises(workoutPlan.getWorkoutPlanExercises(), workoutPlan);
            Set<UUID> exerciseIdsAfterUpdate = workoutPlan.getWorkoutPlanExercises().stream()
                    .map(workoutPlanExercise -> {
                        workoutPlanExercise.setWorkoutPlan(workoutPlan);
                        return workoutPlanExercise.getId().getExerciseId();
                    })
                    .collect(Collectors.toSet());
            workoutPlanExerciseRepo.saveAll(workoutPlan.getWorkoutPlanExercises());
            for (UUID exerciseId : exerciseIdsBeforeUpdate) {
                if (!exerciseIdsAfterUpdate.contains(exerciseId)) {
                    workoutPlanExerciseRepo.deleteByExerciseId(exerciseId);
                }
            }
        }
        WorkoutPlan workoutPlanUpdated = workoutPlanRepo.save(workoutPlan);
        return planMapper.workoutPlanToDto(workoutPlanUpdated);
    }

    @Override
    public void delete(UUID id) {
        workoutPlanRepo.deleteById(id);
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = workoutPlanExerciseRepo.findAllExercises();
        return exerciseMapper.exercisesToDtos(exercises);
    }

    private void assignExerciseAndPlanToWorkoutPlanExercises(List<WorkoutPlanExercise> workoutPlanExercises, WorkoutPlan targetPlan) {
        for (WorkoutPlanExercise workoutPlanExercise : workoutPlanExercises) {
            Exercise exercise = workoutPlanExerciseRepo
                    .findExerciseById(workoutPlanExercise.getId().getExerciseId());
            if (exercise == null) {
                throw new IllegalArgumentException("Invalid exercise ID");
            }
            workoutPlanExercise.setExercise(exercise);
            if (!ObjectUtils.isEmpty(targetPlan)) {
                workoutPlanExercise.setWorkoutPlan(targetPlan);
                workoutPlanExercise.getId().setPlanId(targetPlan.getPlanId());
            }
        }
    }
}

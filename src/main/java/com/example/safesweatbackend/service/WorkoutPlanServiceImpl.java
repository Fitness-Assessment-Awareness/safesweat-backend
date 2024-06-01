package com.example.safesweatbackend.service;

import com.example.safesweatbackend.mapper.WorkoutPlanMapper;
import com.example.safesweatbackend.model.dto.WorkoutPlanDto;
import com.example.safesweatbackend.model.entity.Exercise;
import com.example.safesweatbackend.model.entity.WorkoutPlan;
import com.example.safesweatbackend.model.entity.WorkoutPlanExercise;
import com.example.safesweatbackend.repo.WorkoutPlanExerciseRepo;
import com.example.safesweatbackend.repo.WorkoutPlanRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepo workoutPlanRepo;

    private final WorkoutPlanExerciseRepo workoutPlanExerciseRepo;

    private final WorkoutPlanMapper mapper;

    @Override
    @Transactional
    public WorkoutPlanDto create(WorkoutPlanDto workoutPlanDto) {
        if (!ObjectUtils.isEmpty(workoutPlanDto.getPlanId())) {
            throw new IllegalArgumentException("Plan ID must be null");
        }
        WorkoutPlan workoutPlan = mapper.workoutPlanDtoToWorkoutPlan(workoutPlanDto);
        List<WorkoutPlanExercise> workoutPlanExercises = workoutPlan.getWorkoutPlanExercises();
        WorkoutPlan workoutPlanCreated = workoutPlanRepo.save(workoutPlan);
        mapValidExerciseOrWorkoutPlan(workoutPlanExercises, workoutPlanCreated);
        workoutPlanExerciseRepo.saveAll(workoutPlanExercises);
        return mapper.workoutPlanToDto(workoutPlanCreated);
    }

    @Override
    public WorkoutPlanDto get(UUID id) {
        WorkoutPlan workoutPlan = workoutPlanRepo.findById(id).get();
        return mapper.workoutPlanToDto(workoutPlan);
    }

    @Override
    public List<WorkoutPlanDto> getAll() {
        List<WorkoutPlan> workoutPlans = workoutPlanRepo.findAll();
        return mapper.workoutPlansToDtos(workoutPlans);
    }

    @Override
    @Transactional
    public WorkoutPlanDto update(WorkoutPlanDto workoutPlanDto) {
        UUID planId = workoutPlanDto.getPlanId();
        WorkoutPlan workoutPlan = workoutPlanRepo.findById(planId).get();
        Set<UUID> exerciseIdsBeforeUpdate = workoutPlan.getWorkoutPlanExercises().stream()
                .map(workoutPlanExercise -> workoutPlanExercise.getId().getExerciseId())
                .collect(Collectors.toSet());
        mapper.updateWorkoutPlanFromDto(workoutPlanDto, workoutPlan);
        mapValidExerciseOrWorkoutPlan(workoutPlan.getWorkoutPlanExercises(), workoutPlan);
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
        WorkoutPlan workoutPlanUpdated = workoutPlanRepo.save(workoutPlan);
        return mapper.workoutPlanToDto(workoutPlanUpdated);
    }

    @Override
    public void delete(UUID id) {
        workoutPlanRepo.deleteById(id);
    }

    private void mapValidExerciseOrWorkoutPlan(List<WorkoutPlanExercise> workoutPlanExercises, WorkoutPlan workoutPlan) {
        for (WorkoutPlanExercise workoutPlanExercise : workoutPlanExercises) {
            Exercise exercise = workoutPlanRepo
                    .findExerciseById(workoutPlanExercise.getId().getExerciseId());
            if (exercise == null) {
                throw new IllegalArgumentException("Invalid exercise ID");
            }
            if (!ObjectUtils.isEmpty(workoutPlan)) {
                workoutPlanExercise.setWorkoutPlan(workoutPlan);
                workoutPlanExercise.getId().setPlanId(workoutPlan.getPlanId());
            }
            workoutPlanExercise.setExercise(exercise);
        }
    }
}

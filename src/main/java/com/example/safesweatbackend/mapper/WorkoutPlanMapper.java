package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.WorkoutPlanDto;
import com.example.safesweatbackend.model.entity.WorkoutPlan;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = WorkoutPlanExerciseMapper.class)
public interface WorkoutPlanMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "workoutPlanExerciseDtos", target = "workoutPlanExercises")
    void updateWorkoutPlanFromDto(WorkoutPlanDto workoutPlanDto, @MappingTarget WorkoutPlan workoutPlan);

    @Mapping(source = "workoutPlanExercises", target = "workoutPlanExerciseDtos")
    WorkoutPlanDto workoutPlanToDto(WorkoutPlan workoutPlan);

    @Mapping(source = "workoutPlanExerciseDtos", target = "workoutPlanExercises")
    WorkoutPlan workoutPlanDtoToWorkoutPlan(WorkoutPlanDto workoutPlanDto);

    @Mapping(source = "workoutPlanExerciseDtos", target = "workoutPlanExercises")
    List<WorkoutPlanDto> workoutPlansToDtos(List<WorkoutPlan> workoutPlans);
}

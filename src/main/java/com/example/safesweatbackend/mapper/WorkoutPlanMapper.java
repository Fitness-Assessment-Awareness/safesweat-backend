package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.WorkoutPlanDto;
import com.example.safesweatbackend.model.entity.WorkoutPlan;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkoutPlanExerciseMapper.class, WorkoutFocusAreaMapper.class})
public interface WorkoutPlanMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "workoutPlanExerciseDtos", target = "workoutPlanExercises")
    void updateWorkoutPlanFromDto(WorkoutPlanDto workoutPlanDto, @MappingTarget WorkoutPlan workoutPlan);

    @Mapping(source = "focusArea", target = "focusAreaDto")
    @Mapping(source = "workoutPlanExercises", target = "workoutPlanExerciseDtos")
    WorkoutPlanDto workoutPlanToDto(WorkoutPlan workoutPlan);

    @Mapping(source = "workoutPlanExerciseDtos", target = "workoutPlanExercises")
    WorkoutPlan workoutPlanDtoToWorkoutPlan(WorkoutPlanDto workoutPlanDto);

    @Mapping(source = "focusArea", target = "focusAreaDto")
    @Mapping(source = "workoutPlanExercises", target = "workoutPlanExerciseDtos")
    List<WorkoutPlanDto> workoutPlansToDtos(List<WorkoutPlan> workoutPlans);
}

package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.WorkoutPlanExerciseDto;
import com.example.safesweatbackend.model.entity.WorkoutPlanExercise;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutPlanExerciseMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "exerciseId", target = "id.exerciseId")
    @Mapping(source = "planId", target = "id.planId")
    void updateWorkoutPlanExerciseFromDto(WorkoutPlanExerciseDto workoutPlanExerciseDto, @MappingTarget WorkoutPlanExercise workoutPlanExercise);

    @Mapping(source = "id.exerciseId", target = "exerciseId")
    @Mapping(source = "id.planId", target = "planId")
    @Mapping(source = "exercise.exerciseKey", target = "exerciseKey")
    List<WorkoutPlanExerciseDto> workoutPlanExercisesToDtos(List<WorkoutPlanExercise> workoutPlanExercises);

    @Mapping(source = "exerciseId", target = "id.exerciseId")
    @Mapping(source = "planId", target = "id.planId")
    List<WorkoutPlanExercise> workoutPlanExerciseDtosToWorkoutPlanExercises(List<WorkoutPlanExerciseDto> workoutPlanExerciseDtos);

    @Mapping(source = "id.exerciseId", target = "exerciseId")
    @Mapping(source = "id.planId", target = "planId")
    @Mapping(source = "exercise.exerciseKey", target = "exerciseKey")
    WorkoutPlanExerciseDto workoutPlanExerciseToDto(WorkoutPlanExercise workoutPlanExercise);

    @Mapping(source = "exerciseId", target = "id.exerciseId")
    @Mapping(source = "planId", target = "id.planId")
    WorkoutPlanExercise workoutPlanExerciseDtoToWorkoutPlanExercise(WorkoutPlanExerciseDto workoutPlanExerciseDto);
}

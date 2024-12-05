package com.example.safesweatbackend.model.dto;

import com.example.safesweatbackend.model.type.DifficultyType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutPlanDto {

    private UUID planId;

    private String titleEn;

    private String titleMs;

    private Short estimatedTimeMinute;

    private String imageUrl;

    private String introductionEn;

    private String introductionMs;

    private DifficultyType difficulty;

    private UUID focusAreaId;

    private FocusAreaDto focusAreaDto;

    private List<WorkoutPlanExerciseDto> workoutPlanExerciseDtos;
}

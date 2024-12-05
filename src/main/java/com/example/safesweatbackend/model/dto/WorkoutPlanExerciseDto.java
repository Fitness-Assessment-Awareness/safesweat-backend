package com.example.safesweatbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutPlanExerciseDto {

    private UUID planId;

    private UUID exerciseId;

    private Short estimatedTimeSecond;

    private Short repCount;

    private String exerciseKey;
}

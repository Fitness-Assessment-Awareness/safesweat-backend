package com.example.safesweatbackend.model.dto.backup;

import com.example.safesweatbackend.model.type.DifficultyType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBackupDataDto {

    private UUID userId;

    private String gender;

    private DifficultyType difficulty;

    private Integer weight;

    private Integer height;

    private Integer weeklyGoal;

    private List<UserBackupEmergencyContactDto> userBackupEmergencyContactDtos;

    private List<UserBackupFocusAreaDto> userBackupFocusAreaDtos;

    private List<UserBackupHealthProblemDto> userBackupHealthProblemDtos;

    private List<UserBackupWorkoutHistoryDto> userBackupWorkoutHistoryDtos;
}

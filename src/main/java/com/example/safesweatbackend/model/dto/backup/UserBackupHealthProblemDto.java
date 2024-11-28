package com.example.safesweatbackend.model.dto.backup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBackupHealthProblemDto {

    private UUID userId;

    private String healthProblem;
}

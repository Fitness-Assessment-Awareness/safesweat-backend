package com.example.safesweatbackend.model.dto.backup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBackupEmergencyContactDto {

    private UUID userId;

    private UUID phoneId;

    private String fullName;

    private String phoneNumber;
}

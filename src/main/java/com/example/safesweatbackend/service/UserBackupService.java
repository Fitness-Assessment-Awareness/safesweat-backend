package com.example.safesweatbackend.service;

import com.example.safesweatbackend.model.dto.backup.UserBackupDataDto;

import java.util.UUID;

public interface UserBackupService {

    UserBackupDataDto create(UserBackupDataDto userBackupDataDto);

    UserBackupDataDto get(UUID userId);

    void delete(UUID userId);
}

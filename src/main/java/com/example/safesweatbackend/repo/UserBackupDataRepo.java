package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.backup.UserBackupData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserBackupDataRepo extends JpaRepository<UserBackupData, UUID> {
}

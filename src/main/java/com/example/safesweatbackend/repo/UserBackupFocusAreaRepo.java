package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.backup.UserBackupFocusArea;
import com.example.safesweatbackend.model.entity.backup.UserBackupFocusAreaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBackupFocusAreaRepo extends JpaRepository<UserBackupFocusArea, UserBackupFocusAreaId> {
}

package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.backup.UserBackupWorkoutHistory;
import com.example.safesweatbackend.model.entity.backup.UserBackupWorkoutHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBackupWorkoutHistoryRepo extends JpaRepository<UserBackupWorkoutHistory, UserBackupWorkoutHistoryId> {
}

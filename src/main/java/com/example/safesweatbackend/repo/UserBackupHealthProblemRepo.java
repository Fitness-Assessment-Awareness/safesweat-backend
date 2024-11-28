package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.backup.UserBackupHealthProblem;
import com.example.safesweatbackend.model.entity.backup.UserBackupHealthProblemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBackupHealthProblemRepo extends JpaRepository<UserBackupHealthProblem, UserBackupHealthProblemId> {
}

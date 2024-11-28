package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.backup.UserBackupEmergencyContact;
import com.example.safesweatbackend.model.entity.backup.UserBackupEmergencyContactId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBackupEmergencyContactRepo extends JpaRepository<UserBackupEmergencyContact, UserBackupEmergencyContactId> {
}

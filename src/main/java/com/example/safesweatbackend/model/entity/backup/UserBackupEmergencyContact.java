package com.example.safesweatbackend.model.entity.backup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_backup_emergency_contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBackupEmergencyContact {

    @EmbeddedId
    private UserBackupEmergencyContactId id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserBackupData userBackupData;
}

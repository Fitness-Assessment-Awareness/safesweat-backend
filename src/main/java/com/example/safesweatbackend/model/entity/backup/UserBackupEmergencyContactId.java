package com.example.safesweatbackend.model.entity.backup;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserBackupEmergencyContactId implements Serializable {
    @Serial
    private static final long serialVersionUID = 2951093258998985837L;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "phone_id")
    private UUID phoneId;
}

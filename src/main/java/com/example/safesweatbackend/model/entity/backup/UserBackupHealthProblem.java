package com.example.safesweatbackend.model.entity.backup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_backup_health_problem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBackupHealthProblem {

    @EmbeddedId
    private UserBackupHealthProblemId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserBackupData userBackupData;
}

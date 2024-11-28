package com.example.safesweatbackend.model.entity.backup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_backup_focus_area")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBackupFocusArea {

    @EmbeddedId
    private UserBackupFocusAreaId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserBackupData userBackupData;
}

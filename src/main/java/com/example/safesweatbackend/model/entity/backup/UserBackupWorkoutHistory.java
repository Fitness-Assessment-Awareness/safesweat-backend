package com.example.safesweatbackend.model.entity.backup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_backup_workout_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBackupWorkoutHistory {

    @EmbeddedId
    private UserBackupWorkoutHistoryId id;

    @Column(name = "workout_key")
    private String workoutKey;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "multiplier")
    private Double multiplier;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserBackupData userBackupData;
}

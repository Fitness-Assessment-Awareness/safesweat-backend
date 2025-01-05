package com.example.safesweatbackend.model.entity.backup;

import com.example.safesweatbackend.model.type.DifficultyType;
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

    @Column(name = "type")
    private String type;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "title_ms")
    private String titleMs;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "difficulty")
    private DifficultyType difficulty;

    @Column(name = "calories_burned")
    private Double caloriesBurned;

    @Column(name = "time_taken")
    private Double timeTaken;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserBackupData userBackupData;
}

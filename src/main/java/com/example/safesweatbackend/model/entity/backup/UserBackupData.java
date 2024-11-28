package com.example.safesweatbackend.model.entity.backup;

import com.example.safesweatbackend.model.type.DifficultyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "user_backup_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBackupData {

    @Id
    private UUID userId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "difficulty")
    @Enumerated(EnumType.STRING)
    private DifficultyType difficulty;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weekly_goal")
    private Integer weeklyGoal;

    @OneToMany(mappedBy = "userBackupData", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserBackupEmergencyContact> userBackupEmergencyContacts;

    @OneToMany(mappedBy = "userBackupData", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserBackupFocusArea> userBackupFocusAreas;

    @OneToMany(mappedBy = "userBackupData", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserBackupHealthProblem> userBackupHealthProblems;

    @OneToMany(mappedBy = "userBackupData", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserBackupWorkoutHistory> userBackupWorkoutHistories;
}

package com.example.safesweatbackend.model.entity;

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
public class WorkoutPlanExerciseId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1162008215779445507L;

    @Column(name = "plan_id")
    private UUID planId;

    @Column(name = "exercise_id")
    private UUID exerciseId;
}

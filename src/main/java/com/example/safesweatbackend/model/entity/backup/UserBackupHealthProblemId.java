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
public class UserBackupHealthProblemId implements Serializable {
    @Serial
    private static final long serialVersionUID = 2872342824300492762L;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "health_problem")
    private String healthProblem;
}

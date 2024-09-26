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
public class EducationPostBookmarkId implements Serializable {

    @Serial
    private static final long serialVersionUID = 7420603165168277897L;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "post_id")
    private UUID postId;
}

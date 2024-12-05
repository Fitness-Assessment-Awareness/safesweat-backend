package com.example.safesweatbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "focus_area")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FocusArea {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID focusAreaId;

    @Column(name = "name", nullable = false)
    private String name;
}

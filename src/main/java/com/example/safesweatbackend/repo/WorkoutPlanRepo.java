package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan, UUID> {

}

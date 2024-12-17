package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.constant.TestData;
import com.example.safesweatbackend.model.entity.WorkoutPlan;
import com.example.safesweatbackend.model.entity.WorkoutPlanExercise;
import com.example.safesweatbackend.model.entity.WorkoutPlanExerciseId;
import com.example.safesweatbackend.model.type.DifficultyType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.properties")
class WorkoutPlanModuleRepoTest {

    @Autowired
    private WorkoutPlanRepo workoutPlanRepo;

    @Autowired
    private WorkoutPlanExerciseRepo workoutPlanExerciseRepo;

    private static UUID workoutPlanId;

    private static WorkoutPlanExerciseId workoutPlanExerciseId;

    @Test
    @DisplayName("Test 1: Save Workout Plan")
    @Order(1)
    @Rollback(value = false)
    public void testSaveWorkoutPlan() {
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setDifficulty(DifficultyType.Beginner);
        workoutPlan.setTitleEn("Test Title");
        workoutPlan.setTitleMs("Test Title");
        workoutPlan.setFocusAreaId(UUID.fromString(TestData.FOCUS_AREA_ID));
        workoutPlan.setImageUrl("Test Image URL");
        workoutPlan.setEstimatedTimeMinute((short) 30);
        workoutPlan.setIntroductionEn("Test Introduction");
        workoutPlan.setIntroductionMs("Test Introduction");

        WorkoutPlan createdWorkoutPlan = workoutPlanRepo.save(workoutPlan);

        workoutPlanId = createdWorkoutPlan.getPlanId();
        assertNotNull(createdWorkoutPlan.getPlanId());
    }

    @Test
    @DisplayName("Test 2: Get Workout Plan By Id")
    @Order(2)
    public void testGetWorkoutPlanById() {
        WorkoutPlan workoutPlan = workoutPlanRepo.findById(workoutPlanId).orElse(null);
        assertNotNull(workoutPlan);
    }

    @Test
    @DisplayName("Test 3: Update Workout Plan")
    @Order(3)
    @Rollback(value = false)
    public void testUpdateWorkoutPlan() {
        WorkoutPlan workoutPlan = workoutPlanRepo.findById(workoutPlanId).orElse(null);
        assertNotNull(workoutPlan);

        String newTitle = "Updated Title";
        workoutPlan.setTitleEn(newTitle);

        WorkoutPlan updatedWorkoutPlan = workoutPlanRepo.save(workoutPlan);
        assertEquals(newTitle, updatedWorkoutPlan.getTitleEn());
    }

    @Test
    @DisplayName("Test 4: Save Workout Plan Exercise")
    @Order(4)
    @Rollback(value = false)
    public void testSaveWorkoutPlanExercise() {
        WorkoutPlanExercise workoutPlanExercise = new WorkoutPlanExercise();
        workoutPlanExercise.setId(new WorkoutPlanExerciseId(workoutPlanId, UUID.fromString(TestData.EXERCISE_ID)));
        workoutPlanExercise.setRepCount((short) 1);
        workoutPlanExercise.setWorkoutPlan(workoutPlanRepo.findById(workoutPlanId).orElse(null));
        workoutPlanExercise.setExercise(workoutPlanExerciseRepo.findExerciseById(UUID.fromString(TestData.EXERCISE_ID)));

        WorkoutPlanExercise createdWorkoutPlanExercise = workoutPlanExerciseRepo.save(workoutPlanExercise);

        workoutPlanExerciseId = createdWorkoutPlanExercise.getId();
        assertNotNull(createdWorkoutPlanExercise);
    }

    @Test
    @DisplayName("Test 5: Get Workout Plan Exercise By Id")
    @Order(5)
    public void testGetWorkoutPlanExerciseById() {
        WorkoutPlanExercise workoutPlanExercise = workoutPlanExerciseRepo.findById(workoutPlanExerciseId).orElse(null);
        assertNotNull(workoutPlanExercise);
    }

    @Test
    @DisplayName("Test 6: Delete Workout Plan Exercise")
    @Order(6)
    @Rollback(value = false)
    public void testDeleteWorkoutPlanExercise() {
        workoutPlanExerciseRepo.deleteById(workoutPlanExerciseId);
        WorkoutPlanExercise workoutPlanExercise = workoutPlanExerciseRepo.findById(workoutPlanExerciseId).orElse(null);
        assertNull(workoutPlanExercise);
    }

    @Test
    @DisplayName("Test 7: Delete Workout Plan")
    @Order(7)
    @Rollback(value = false)
    public void testDeleteWorkoutPlan() {
        workoutPlanRepo.deleteById(workoutPlanId);
        WorkoutPlan workoutPlan = workoutPlanRepo.findById(workoutPlanId).orElse(null);
        assertNull(workoutPlan);
    }
}

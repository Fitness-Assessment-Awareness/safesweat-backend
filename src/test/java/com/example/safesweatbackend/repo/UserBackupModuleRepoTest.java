package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.model.entity.backup.*;
import com.example.safesweatbackend.model.type.DifficultyType;
import com.example.safesweatbackend.model.type.GenderType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.properties")
class UserBackupModuleRepoTest {

    @Autowired
    private UserBackupDataRepo userBackupDataRepo;

    @Autowired
    private UserBackupEmergencyContactRepo userBackupEmergencyContactRepo;

    @Autowired
    private UserBackupFocusAreaRepo userBackupFocusAreaRepo;

    @Autowired
    private UserBackupHealthProblemRepo userBackupHealthProblemRepo;

    @Autowired
    private UserBackupWorkoutHistoryRepo userBackupWorkoutHistoryRepo;

    private static UUID userId;

    @Test
    @DisplayName("Test 1: Save UserBackup Data")
    @Order(1)
    @Rollback(value = false)
    public void testSaveUserBackupData() {
        UserBackupData userBackupData = new UserBackupData();
        userBackupData.setUserId(UUID.randomUUID());
        userBackupData.setDifficulty(DifficultyType.Beginner);
        userBackupData.setGender(GenderType.MALE.toString());
        userBackupData.setWeeklyGoal(3);
        userBackupData.setWeight(70);
        userBackupData.setHeight(180);

        UserBackupData createdBackupData = userBackupDataRepo.save(userBackupData);

        userId = createdBackupData.getUserId();
        assertNotNull(createdBackupData.getUserId());
    }

    @Test
    @DisplayName("Test 2: Get User Backup Data By Id")
    @Order(2)
    public void testGetUserBackupDataById() {
        UserBackupData userBackupData = userBackupDataRepo.findById(userId).orElse(null);
        assertNotNull(userBackupData);
    }

    @Test
    @DisplayName("Test 3: Save User Backup Emergency Contact")
    @Order(3)
    @Rollback(value = false)
    public void testSaveUserBackupEmergencyContact() {
        UserBackupEmergencyContact userBackupEmergencyContact = new UserBackupEmergencyContact();
        userBackupEmergencyContact.setFullName("Hospital");
        userBackupEmergencyContact.setPhoneNumber("999");
        userBackupEmergencyContact.setId(new UserBackupEmergencyContactId(userId, UUID.randomUUID()));
        userBackupEmergencyContact.setUserBackupData(userBackupDataRepo.findById(userId).orElse(null));

        UserBackupEmergencyContact createdEmergencyContact = userBackupEmergencyContactRepo.save(userBackupEmergencyContact);
        assertNotNull(createdEmergencyContact.getId());
    }

    @Test
    @DisplayName("Test 4: Save User Backup Focus Area")
    @Order(4)
    @Rollback(value = false)
    public void testSaveUserBackupFocusArea() {
        UserBackupFocusArea userBackupFocusArea = new UserBackupFocusArea();
        userBackupFocusArea.setId(new UserBackupFocusAreaId(userId, "Full Body"));
        userBackupFocusArea.setUserBackupData(userBackupDataRepo.findById(userId).orElse(null));

        UserBackupFocusArea createdFocusArea = userBackupFocusAreaRepo.save(userBackupFocusArea);
        assertNotNull(createdFocusArea.getId());
    }

    @Test
    @DisplayName("Test 5: Save User Backup Health Problem")
    @Order(5)
    @Rollback(value = false)
    public void testSaveUserBackupHealthProblem() {
        UserBackupHealthProblem userBackupHealthProblem = new UserBackupHealthProblem();
        userBackupHealthProblem.setId(new UserBackupHealthProblemId(userId, "Heart Conditions"));
        userBackupHealthProblem.setUserBackupData(userBackupDataRepo.findById(userId).orElse(null));

        UserBackupHealthProblem createdHealthProblem = userBackupHealthProblemRepo.save(userBackupHealthProblem);
        assertNotNull(createdHealthProblem.getId());
    }

    @Test
    @DisplayName("Test 6: Save User Backup Workout History")
    @Order(6)
    @Rollback(value = false)
    public void testSaveUserBackupWorkoutHistory() {
        UserBackupWorkoutHistory userBackupWorkoutHistory = new UserBackupWorkoutHistory();
        userBackupWorkoutHistory.setId(new UserBackupWorkoutHistoryId(userId, Instant.now().toString()));
        userBackupWorkoutHistory.setUserBackupData(userBackupDataRepo.findById(userId).orElse(null));

        UserBackupWorkoutHistory createdWorkoutHistory = userBackupWorkoutHistoryRepo.save(userBackupWorkoutHistory);
        assertNotNull(createdWorkoutHistory.getId());
    }

    @Test
    @DisplayName("Test 7: Delete User Backup Data By Id")
    @Order(7)
    @Rollback(value = false)
    public void testDeleteUserBackupDataById() {
        userBackupDataRepo.deleteById(userId);
        UserBackupData userBackupData = userBackupDataRepo.findById(userId).orElse(null);
        assertNull(userBackupData);
    }
}

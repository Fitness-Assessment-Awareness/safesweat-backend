package com.example.safesweatbackend.service;

import com.example.safesweatbackend.constant.TestData;
import com.example.safesweatbackend.mapper.WorkoutPlanMapper;
import com.example.safesweatbackend.model.dto.WorkoutPlanDto;
import com.example.safesweatbackend.model.entity.WorkoutPlan;
import com.example.safesweatbackend.model.type.DifficultyType;
import com.example.safesweatbackend.repo.WorkoutPlanRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class
WorkoutPlanServiceTest {

    @Mock
    private WorkoutPlanRepo workoutPlanRepo;

    @Mock
    private WorkoutPlanMapper workoutPlanMapper;

    @InjectMocks
    private WorkoutPlanServiceImpl workoutPlanService;

    private WorkoutPlanDto workoutPlanDto;

    private WorkoutPlan workoutPlan;

    @BeforeEach
    public void setup() {
        workoutPlanDto = new WorkoutPlanDto();
        workoutPlanDto.setDifficulty(DifficultyType.Beginner);
        workoutPlanDto.setTitleEn("Test Title");
        workoutPlanDto.setTitleMs("Test Title");
        workoutPlanDto.setFocusAreaId(UUID.fromString(TestData.FOCUS_AREA_ID));
        workoutPlanDto.setImageUrl("Test Image URL");
        workoutPlanDto.setEstimatedTimeMinute((short) 30);
        workoutPlanDto.setIntroductionEn("Test Introduction");
        workoutPlanDto.setIntroductionMs("Test Introduction");

        workoutPlan = new WorkoutPlan();
        workoutPlan.setDifficulty(workoutPlanDto.getDifficulty());
        workoutPlan.setTitleEn(workoutPlanDto.getTitleEn());
        workoutPlan.setTitleMs(workoutPlanDto.getTitleMs());
        workoutPlan.setFocusAreaId(workoutPlanDto.getFocusAreaId());
        workoutPlan.setImageUrl(workoutPlanDto.getImageUrl());
        workoutPlan.setEstimatedTimeMinute(workoutPlanDto.getEstimatedTimeMinute());
        workoutPlan.setIntroductionEn(workoutPlanDto.getIntroductionEn());
        workoutPlan.setIntroductionMs(workoutPlanDto.getIntroductionMs());
    }

    @Test
    @DisplayName("Test 1: Save Workout Plan")
    @Order(1)
    public void testSaveWorkoutPlan() {
        given(workoutPlanMapper.workoutPlanDtoToWorkoutPlan(workoutPlanDto)).willReturn(workoutPlan);
        given(workoutPlanRepo.save(workoutPlan)).willReturn(workoutPlan);
        given(workoutPlanMapper.workoutPlanToDto(workoutPlan)).willReturn(workoutPlanDto);

        WorkoutPlanDto savedWorkoutPlan = workoutPlanService.create(workoutPlanDto);

        assertNotNull(savedWorkoutPlan);
        assertEquals(workoutPlanDto.getPlanId(), savedWorkoutPlan.getPlanId());
        assertEquals(workoutPlanDto.getDifficulty(), savedWorkoutPlan.getDifficulty());
    }

    @Test
    @DisplayName("Test 2: Get Workout Plan By Id")
    @Order(2)
    public void testGetWorkoutPlanById() {
        workoutPlanDto.setPlanId(UUID.randomUUID());
        workoutPlan.setPlanId(workoutPlanDto.getPlanId());
        given(workoutPlanRepo.findById(workoutPlanDto.getPlanId())).willReturn(Optional.of(workoutPlan));
        given(workoutPlanMapper.workoutPlanToDto(workoutPlan)).willReturn(workoutPlanDto);

        WorkoutPlanDto foundWorkoutPlan = workoutPlanService.get(workoutPlanDto.getPlanId());

        assertNotNull(foundWorkoutPlan);
        assertEquals(workoutPlanDto.getPlanId(), foundWorkoutPlan.getPlanId());
        assertEquals(workoutPlanDto.getDifficulty(), foundWorkoutPlan.getDifficulty());
    }

    @Test
    @DisplayName("Test 3: Update Workout Plan")
    @Order(3)
    public void testUpdateWorkoutPlan() {
        WorkoutPlanDto newWorkoutPlanDto = new WorkoutPlanDto();
        newWorkoutPlanDto.setPlanId(workoutPlanDto.getPlanId());
        String newTitle = "Updated Title";
        newWorkoutPlanDto.setTitleEn(newTitle);

        WorkoutPlan newWorkoutPlan = new WorkoutPlan();
        newWorkoutPlan.setPlanId(newWorkoutPlanDto.getPlanId());
        newWorkoutPlan.setTitleEn(newWorkoutPlanDto.getTitleEn());

        given(workoutPlanRepo.save(newWorkoutPlan)).willReturn(newWorkoutPlan);
        given(workoutPlanMapper.workoutPlanToDto(newWorkoutPlan)).willReturn(newWorkoutPlanDto);
        given(workoutPlanRepo.findById(newWorkoutPlanDto.getPlanId())).willReturn(Optional.of(newWorkoutPlan));

        WorkoutPlanDto updatedWorkoutPlanDto = workoutPlanService.update(newWorkoutPlanDto);

        assertNotNull(updatedWorkoutPlanDto);
        assertEquals(newWorkoutPlanDto.getPlanId(), updatedWorkoutPlanDto.getPlanId());
        assertEquals(newTitle, updatedWorkoutPlanDto.getTitleEn());
    }

    @Test
    @DisplayName("Test 4: Delete Workout Plan")
    @Order(4)
    public void testDeleteWorkoutPlan() {
        willDoNothing().given(workoutPlanRepo).deleteById(workoutPlanDto.getPlanId());
        assertDoesNotThrow(() -> workoutPlanService.delete(workoutPlanDto.getPlanId()));
    }
}
package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.model.dto.ExerciseDto;
import com.example.safesweatbackend.model.dto.WorkoutPlanDto;
import com.example.safesweatbackend.model.type.DifficultyType;
import com.example.safesweatbackend.service.WorkoutPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutPlanController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WorkoutPlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkoutPlanService workoutPlanService;

    @Autowired
    private ObjectMapper objectMapper;

    private WorkoutPlanDto workoutPlanDto;

    @BeforeEach
    public void setup() {
        workoutPlanDto = new WorkoutPlanDto();
        workoutPlanDto.setPlanId(UUID.randomUUID());
        workoutPlanDto.setTitleEn("Test Plan");
        workoutPlanDto.setIntroductionEn("Test Description");
        workoutPlanDto.setImageUrl("Test Image URL");
        workoutPlanDto.setDifficulty(DifficultyType.Intermediate);
    }

    @Test
    @DisplayName("Test 1: Save Workout Plan")
    @Order(1)
    public void testSaveWorkoutPlan() throws Exception {
        given(workoutPlanService.create(any(WorkoutPlanDto.class))).willReturn(workoutPlanDto);
        ResultActions response = mockMvc.perform(post("/workout-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(workoutPlanDto))
        );
        response.andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.titleEn").value(workoutPlanDto.getTitleEn()))
                .andExpect(jsonPath("$.introductionEn").value(workoutPlanDto.getIntroductionEn()));
    }

    @Test
    @DisplayName("Test 2: Get Workout Plan By Id")
    @Order(2)
    public void testGetWorkoutPlanById() throws Exception {
        given(workoutPlanService.get(workoutPlanDto.getPlanId())).willReturn(workoutPlanDto);

        ResultActions response = mockMvc.perform(get("/workout-plan/{planId}", workoutPlanDto.getPlanId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.titleEn").value(workoutPlanDto.getTitleEn()))
                .andExpect(jsonPath("$.introductionEn").value(workoutPlanDto.getIntroductionEn()));
    }

    @Test
    @DisplayName("Test 3: Update Workout Plan")
    @Order(3)
    public void testUpdateWorkoutPlan() throws Exception {

        WorkoutPlanDto updatedWorkoutPlanDto = new WorkoutPlanDto();
        updatedWorkoutPlanDto.setPlanId(workoutPlanDto.getPlanId());

        String newTitle = "Updated Title";
        updatedWorkoutPlanDto.setTitleEn(newTitle);
        given(workoutPlanService.update(updatedWorkoutPlanDto)).willReturn(updatedWorkoutPlanDto);

        ResultActions response = mockMvc.perform(patch("/workout-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWorkoutPlanDto))
        );

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.titleEn").value(newTitle));
    }

    @Test
    @DisplayName("Test 4: Delete Workout Plan")
    @Order(4)
    public void testDeleteWorkoutPlan() throws Exception {
        willDoNothing().given(workoutPlanService).delete(workoutPlanDto.getPlanId());

        ResultActions response = mockMvc.perform(delete("/workout-plan/{id}", workoutPlanDto.getPlanId()));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Get Workout Plans List")
    @Order(5)
    public void getWorkoutPlansList() throws Exception {
        given(workoutPlanService.getAll()).willReturn(List.of(workoutPlanDto));
        ResultActions response = mockMvc.perform(get("/workout-plan/list"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].titleEn").value(workoutPlanDto.getTitleEn()));
    }

    @Test
    @DisplayName("Get Workout Plan with Invalid ID")
    @Order(6)
    public void getWorkoutPlanWithInvalidId() throws Exception {
        UUID invalidId = UUID.randomUUID();
        given(workoutPlanService.get(invalidId)).willReturn(null);

        ResultActions response = mockMvc.perform(get("/workout-plan/{id}", invalidId));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Update Workout Plan with Missing Fields")
    @Order(7)
    public void updateWorkoutPlanWithMissingFields() throws Exception {
        WorkoutPlanDto incompleteDto = new WorkoutPlanDto();
        incompleteDto.setPlanId(UUID.randomUUID());

        ResultActions response = mockMvc.perform(patch("/workout-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incompleteDto))
        );

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Delete Workout Plan with Invalid ID")
    @Order(8)
    public void deleteWorkoutPlanWithInvalidId() throws Exception {
        UUID invalidId = UUID.randomUUID();
        willDoNothing().given(workoutPlanService).delete(invalidId);

        ResultActions response = mockMvc.perform(delete("/workout-plan/{id}", invalidId));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Get Exercises List")
    @Order(9)
    public void getExercisesList() throws Exception {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setExerciseId(UUID.randomUUID());
        exerciseDto.setName("Test Exercise");
        given(workoutPlanService.getAllExercises()).willReturn(List.of(exerciseDto));

        ResultActions response = mockMvc.perform(get("/workout-plan/exercises"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value(exerciseDto.getName()));
    }
}
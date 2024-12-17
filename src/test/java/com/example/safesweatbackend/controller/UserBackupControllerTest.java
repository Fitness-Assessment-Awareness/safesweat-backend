package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.model.dto.backup.UserBackupDataDto;
import com.example.safesweatbackend.model.type.DifficultyType;
import com.example.safesweatbackend.model.type.GenderType;
import com.example.safesweatbackend.service.UserBackupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserBackupController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserBackupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserBackupService userBackupService;

    private UserBackupDataDto userBackupDataDto;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        userBackupDataDto = new UserBackupDataDto();
        userBackupDataDto.setUserId(UUID.randomUUID());
        userBackupDataDto.setDifficulty(DifficultyType.Beginner);
        userBackupDataDto.setGender(GenderType.MALE.toString());
        userBackupDataDto.setWeeklyGoal(3);
        userBackupDataDto.setWeight(70);
        userBackupDataDto.setHeight(180);
    }

    @Test
    @DisplayName("Test 1: Save User Backup Data")
    @Order(1)
    public void testSaveUserBackupData() throws Exception {
        given(userBackupService.create(any(UserBackupDataDto.class))).willReturn(userBackupDataDto);
        ResultActions response = mockMvc.perform(post("/user-backup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userBackupDataDto))
        );
        response.andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userBackupDataDto.getUserId().toString()))
                .andExpect(jsonPath("$.difficulty").value(userBackupDataDto.getDifficulty().toString()));
    }

    @Test
    @DisplayName("Test 2: Get User Backup Data By Id")
    @Order(2)
    public void testGetUserBackupDataById() throws Exception {
        given(userBackupService.get(any(UUID.class))).willReturn(userBackupDataDto);
        ResultActions response = mockMvc.perform(get("/user-backup/{userId}", userBackupDataDto.getUserId()));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId").value(userBackupDataDto.getUserId().toString()))
                .andExpect(jsonPath("$.difficulty").value(userBackupDataDto.getDifficulty().toString()));
    }

    @Test
    @DisplayName("Test 3: Delete User Backup Data")
    @Order(3)
    public void testDeleteUserBackupData() throws Exception {
        willDoNothing().given(userBackupService).delete(userBackupDataDto.getUserId());

        ResultActions response = mockMvc.perform(delete("/user-backup/{userId}", userBackupDataDto.getUserId()));

        response.andExpect(status().isAccepted())
                .andDo(print());
    }
}
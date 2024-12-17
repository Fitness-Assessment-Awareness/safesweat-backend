package com.example.safesweatbackend.service;

import com.example.safesweatbackend.mapper.backup.UserBackupDataMapper;
import com.example.safesweatbackend.model.dto.backup.UserBackupDataDto;
import com.example.safesweatbackend.model.entity.backup.UserBackupData;
import com.example.safesweatbackend.model.type.DifficultyType;
import com.example.safesweatbackend.model.type.GenderType;
import com.example.safesweatbackend.repo.UserBackupDataRepo;
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
class UserBackupServiceTest {

    @Mock
    private UserBackupDataRepo userBackupDataRepo;

    @Mock
    private UserBackupDataMapper userBackupDataMapper;

    @InjectMocks
    private UserBackupServiceImpl userBackupService;

    private UserBackupDataDto userBackupDataDto;

    private UserBackupData userBackupData;

    @BeforeEach
    public void setup() {
        userBackupDataDto = new UserBackupDataDto();
        userBackupDataDto.setUserId(UUID.randomUUID());
        userBackupDataDto.setDifficulty(DifficultyType.Beginner);
        userBackupDataDto.setGender(GenderType.MALE.toString());
        userBackupDataDto.setWeeklyGoal(3);
        userBackupDataDto.setWeight(70);
        userBackupDataDto.setHeight(180);

        userBackupData = new UserBackupData();
        userBackupDataDto.setUserId(userBackupDataDto.getUserId());
        userBackupDataDto.setDifficulty(userBackupDataDto.getDifficulty());
        userBackupDataDto.setGender(userBackupDataDto.getGender());
        userBackupDataDto.setWeeklyGoal(userBackupDataDto.getWeeklyGoal());
        userBackupDataDto.setWeight(userBackupDataDto.getWeight());
        userBackupDataDto.setHeight(userBackupDataDto.getHeight());
    }

    @Test
    @DisplayName("Test 1: Save User Backup Data")
    @Order(1)
    public void testSaveUserBackupData() {
        given(userBackupDataMapper.userBackupDataDtoToBackupData(userBackupDataDto)).willReturn(userBackupData);
        given(userBackupDataRepo.save(userBackupData)).willReturn(userBackupData);
        given(userBackupDataMapper.userBackupDataToDto(userBackupData)).willReturn(userBackupDataDto);

        UserBackupDataDto savedUserBackupDataDto = userBackupService.create(userBackupDataDto);

        assertNotNull(savedUserBackupDataDto);
        assertEquals(userBackupDataDto.getUserId(), savedUserBackupDataDto.getUserId());
        assertEquals(userBackupDataDto.getDifficulty(), savedUserBackupDataDto.getDifficulty());
        assertEquals(userBackupDataDto.getGender(), savedUserBackupDataDto.getGender());
        assertEquals(userBackupDataDto.getWeeklyGoal(), savedUserBackupDataDto.getWeeklyGoal());
        assertEquals(userBackupDataDto.getWeight(), savedUserBackupDataDto.getWeight());
        assertEquals(userBackupDataDto.getHeight(), savedUserBackupDataDto.getHeight());
    }

    @Test
    @DisplayName("Test 2: Get User Backup Data By Id")
    @Order(2)
    public void testGetUserBackupDataById() {
        given(userBackupDataRepo.findById(userBackupDataDto.getUserId())).willReturn(Optional.of(userBackupData));
        given(userBackupDataMapper.userBackupDataToDto(userBackupData)).willReturn(userBackupDataDto);

        UserBackupDataDto foundUserBackupDataDto = userBackupService.get(userBackupDataDto.getUserId());

        assertNotNull(foundUserBackupDataDto);
        assertEquals(userBackupDataDto.getUserId(), foundUserBackupDataDto.getUserId());
        assertEquals(userBackupDataDto.getDifficulty(), foundUserBackupDataDto.getDifficulty());
        assertEquals(userBackupDataDto.getGender(), foundUserBackupDataDto.getGender());
        assertEquals(userBackupDataDto.getWeeklyGoal(), foundUserBackupDataDto.getWeeklyGoal());
        assertEquals(userBackupDataDto.getWeight(), foundUserBackupDataDto.getWeight());
        assertEquals(userBackupDataDto.getHeight(), foundUserBackupDataDto.getHeight());
    }

    @Test
    @DisplayName("Test 3: Delete User Backup Data")
    @Order(3)
    public void testDeleteUserBackupData() {
        willDoNothing().given(userBackupDataRepo).deleteById(userBackupDataDto.getUserId());
        assertDoesNotThrow(() -> userBackupService.delete(userBackupDataDto.getUserId()));
    }
}
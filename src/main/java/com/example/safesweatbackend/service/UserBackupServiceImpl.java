package com.example.safesweatbackend.service;

import com.example.safesweatbackend.mapper.backup.UserBackupDataMapper;
import com.example.safesweatbackend.model.dto.backup.UserBackupDataDto;
import com.example.safesweatbackend.model.entity.backup.*;
import com.example.safesweatbackend.model.type.FocusAreaType;
import com.example.safesweatbackend.model.type.GenderType;
import com.example.safesweatbackend.model.type.HealthProblemType;
import com.example.safesweatbackend.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBackupServiceImpl implements UserBackupService {

    private final UserBackupDataRepo userBackupDataRepo;

    private final UserBackupEmergencyContactRepo userBackupEmergencyContactRepo;

    private final UserBackupFocusAreaRepo userBackupFocusAreaRepo;

    private final UserBackupHealthProblemRepo userBackupHealthProblemRepo;

    private final UserBackupWorkoutHistoryRepo userBackupWorkoutHistoryRepo;

    private final UserBackupDataMapper userBackupDataMapper;

    @Override
    @Transactional
    public UserBackupDataDto create(UserBackupDataDto userBackupDataDto) {
        if (userBackupDataDto == null) {
            throw new IllegalArgumentException("UserBackupDataDto cannot be null");
        }
        if (!CollectionUtils.isEmpty(userBackupDataDto.getUserBackupFocusAreaDtos())) {
            userBackupDataDto.getUserBackupFocusAreaDtos().forEach(focusAreaDto -> {
                if (!FocusAreaType.isValidFocusArea(focusAreaDto.getFocusArea())) {
                    throw new IllegalArgumentException("Invalid focus area: " + focusAreaDto.getFocusArea());
                }
            });
        }
        if (!CollectionUtils.isEmpty(userBackupDataDto.getUserBackupHealthProblemDtos())) {
            userBackupDataDto.getUserBackupHealthProblemDtos().forEach(healthProblemDto -> {
                if (!HealthProblemType.isValidHealthProblem(healthProblemDto.getHealthProblem())) {
                    throw new IllegalArgumentException("Invalid health problem: " + healthProblemDto.getHealthProblem());
                }
            });
        }
        if (userBackupDataDto.getGender() != null) {
            if (!GenderType.isValidGender(userBackupDataDto.getGender())) {
                throw new IllegalArgumentException("Invalid gender: " + userBackupDataDto.getGender());
            }
        }
        userBackupDataRepo.findById(userBackupDataDto.getUserId()).ifPresent(existingBackupData -> userBackupDataRepo.deleteById(userBackupDataDto.getUserId()));

        UserBackupData userBackupData = userBackupDataMapper.userBackupDataDtoToBackupData(userBackupDataDto);
        List<UserBackupEmergencyContact> userBackupEmergencyContacts = userBackupData.getUserBackupEmergencyContacts();
        List<UserBackupFocusArea> userBackupFocusAreas = userBackupData.getUserBackupFocusAreas();
        List<UserBackupHealthProblem> userBackupHealthProblems = userBackupData.getUserBackupHealthProblems();
        List<UserBackupWorkoutHistory> userBackupWorkoutHistories = userBackupData.getUserBackupWorkoutHistories();

        userBackupData.setUserBackupEmergencyContacts(null);
        userBackupData.setUserBackupFocusAreas(null);
        userBackupData.setUserBackupHealthProblems(null);
        userBackupData.setUserBackupWorkoutHistories(null);

        UserBackupData createdBackupData = userBackupDataRepo.save(userBackupData);

        if (!CollectionUtils.isEmpty(userBackupEmergencyContacts)) {
            userBackupEmergencyContacts.forEach(emergencyContact -> emergencyContact.setUserBackupData(createdBackupData));
            userBackupEmergencyContactRepo.saveAll(userBackupEmergencyContacts);
        }

        if (!CollectionUtils.isEmpty(userBackupFocusAreas)) {
            userBackupFocusAreas.forEach(focusArea -> focusArea.setUserBackupData(createdBackupData));
            userBackupFocusAreaRepo.saveAll(userBackupFocusAreas);
        }

        if (!CollectionUtils.isEmpty(userBackupHealthProblems)) {
            userBackupHealthProblems.forEach(healthProblem -> healthProblem.setUserBackupData(createdBackupData));
            userBackupHealthProblemRepo.saveAll(userBackupHealthProblems);
        }

        if (!CollectionUtils.isEmpty(userBackupWorkoutHistories)) {
            userBackupWorkoutHistories.forEach(workoutHistory -> workoutHistory.setUserBackupData(createdBackupData));
            userBackupWorkoutHistoryRepo.saveAll(userBackupWorkoutHistories);
        }
        return userBackupDataMapper.userBackupDataToDto(createdBackupData);
    }

    @Override
    public UserBackupDataDto get(UUID userId) {
        UserBackupData userBackupData = userBackupDataRepo.findById(userId).orElse(null);
        if (userBackupData == null) {
            return null;
        }
        return userBackupDataMapper.userBackupDataToDto(userBackupData);
    }

    @Override
    public void delete(UUID userId) {
        userBackupDataRepo.deleteById(userId);
    }
}

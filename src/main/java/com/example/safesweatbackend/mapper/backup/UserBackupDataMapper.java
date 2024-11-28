package com.example.safesweatbackend.mapper.backup;

import com.example.safesweatbackend.model.dto.backup.UserBackupDataDto;
import com.example.safesweatbackend.model.entity.backup.UserBackupData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserBackupEmergencyContactMapper.class, UserBackupFocusAreaMapper.class, UserBackupHealthProblemMapper.class, UserBackupWorkoutHistoryMapper.class})
public interface UserBackupDataMapper {

    @Mapping(source = "userBackupEmergencyContacts", target = "userBackupEmergencyContactDtos")
    @Mapping(source = "userBackupFocusAreas", target = "userBackupFocusAreaDtos")
    @Mapping(source = "userBackupHealthProblems", target = "userBackupHealthProblemDtos")
    @Mapping(source = "userBackupWorkoutHistories", target = "userBackupWorkoutHistoryDtos")
    UserBackupDataDto userBackupDataToDto(UserBackupData userBackupData);

    @Mapping(source = "userBackupEmergencyContactDtos", target = "userBackupEmergencyContacts")
    @Mapping(source = "userBackupFocusAreaDtos", target = "userBackupFocusAreas")
    @Mapping(source = "userBackupHealthProblemDtos", target = "userBackupHealthProblems")
    @Mapping(source = "userBackupWorkoutHistoryDtos", target = "userBackupWorkoutHistories")
    UserBackupData userBackupDataDtoToBackupData(UserBackupDataDto userBackupDataDto);

}

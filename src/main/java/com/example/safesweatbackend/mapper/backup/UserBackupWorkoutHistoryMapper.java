package com.example.safesweatbackend.mapper.backup;

import com.example.safesweatbackend.model.dto.backup.UserBackupWorkoutHistoryDto;
import com.example.safesweatbackend.model.entity.backup.UserBackupWorkoutHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBackupWorkoutHistoryMapper {

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.timestamp", target = "timestamp")
    List<UserBackupWorkoutHistoryDto> userBackupWorkoutHistoriesToDtos(List<UserBackupWorkoutHistory> userBackupWorkoutHistories);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "timestamp", target = "id.timestamp")
    List<UserBackupWorkoutHistory> userBackupWorkoutHistoryDtosToWorkoutHistories(List<UserBackupWorkoutHistoryDto> userBackupWorkoutHistoryDtos);

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.timestamp", target = "timestamp")
    UserBackupWorkoutHistoryDto userBackupWorkoutHistoryToDto(UserBackupWorkoutHistory userBackupWorkoutHistory);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "timestamp", target = "id.timestamp")
    UserBackupWorkoutHistory userBackupWorkoutHistoryDtoToWorkoutHistory(UserBackupWorkoutHistoryDto userBackupWorkoutHistoryDto);
}

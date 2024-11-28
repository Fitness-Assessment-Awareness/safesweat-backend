package com.example.safesweatbackend.mapper.backup;

import com.example.safesweatbackend.model.dto.backup.UserBackupFocusAreaDto;
import com.example.safesweatbackend.model.entity.backup.UserBackupFocusArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBackupFocusAreaMapper {

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.focusArea", target = "focusArea")
    List<UserBackupFocusAreaDto> userBackupFocusAreasToDtos(List<UserBackupFocusArea> userBackupFocusAreas);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "focusArea", target = "id.focusArea")
    List<UserBackupFocusArea> userBackupFocusAreaDtosToFocusAreas(List<UserBackupFocusAreaDto> userBackupFocusAreaDtos);

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.focusArea", target = "focusArea")
    UserBackupFocusAreaDto userBackupFocusAreaToDto(UserBackupFocusArea userBackupFocusArea);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "focusArea", target = "id.focusArea")
    UserBackupFocusArea userBackupFocusAreaDtoToFocusArea(UserBackupFocusAreaDto userBackupFocusAreaDto);
}

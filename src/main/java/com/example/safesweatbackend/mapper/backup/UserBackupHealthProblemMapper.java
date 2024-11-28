package com.example.safesweatbackend.mapper.backup;

import com.example.safesweatbackend.model.dto.backup.UserBackupHealthProblemDto;
import com.example.safesweatbackend.model.entity.backup.UserBackupHealthProblem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBackupHealthProblemMapper {

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.healthProblem", target = "healthProblem")
    List<UserBackupHealthProblemDto> userBackupHealthProblemsToDtos(List<UserBackupHealthProblem> userBackupHealthProblems);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "healthProblem", target = "id.healthProblem")
    List<UserBackupHealthProblem> userBackupHealthProblemDtosToHealthProblems(List<UserBackupHealthProblemDto> userBackupHealthProblemDtos);

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.healthProblem", target = "healthProblem")
    UserBackupHealthProblemDto userBackupHealthProblemToDto(UserBackupHealthProblem userBackupHealthProblem);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "healthProblem", target = "id.healthProblem")
    UserBackupHealthProblem userBackupHealthProblemDtoToHealthProblem(UserBackupHealthProblemDto userBackupHealthProblemDto);
}

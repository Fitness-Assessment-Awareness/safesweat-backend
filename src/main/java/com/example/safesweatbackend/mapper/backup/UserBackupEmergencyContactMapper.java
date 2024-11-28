package com.example.safesweatbackend.mapper.backup;

import com.example.safesweatbackend.model.dto.backup.UserBackupEmergencyContactDto;
import com.example.safesweatbackend.model.entity.backup.UserBackupEmergencyContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBackupEmergencyContactMapper {

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.phoneId", target = "phoneId")
    List<UserBackupEmergencyContactDto> userBackupEmergencyContactsToDtos(List<UserBackupEmergencyContact> userBackupEmergencyContacts);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "phoneId", target = "id.phoneId")
    List<UserBackupEmergencyContact> userBackupEmergencyContactDtosToEmergencyContacts(List<UserBackupEmergencyContactDto> userBackupEmergencyContactDtos);

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.phoneId", target = "phoneId")
    UserBackupEmergencyContactDto userBackupEmergencyContactToDto(UserBackupEmergencyContact userBackupEmergencyContact);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "phoneId", target = "id.phoneId")
    UserBackupEmergencyContact userBackupEmergencyContactDtoToEmergencyContact(UserBackupEmergencyContactDto userBackupEmergencyContactDto);
}

package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.EducationPostBookmarkDto;
import com.example.safesweatbackend.model.entity.EducationPostBookmark;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationPostBookmarkMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "postId", target = "id.postId")
    @Mapping(source = "userId", target = "id.userId")
    void updateEducationPostBookmarkFromDto(EducationPostBookmarkDto educationPostBookmarkDto, @MappingTarget EducationPostBookmark educationPostBookmark);

    @Mapping(source = "id.postId", target = "postId")
    @Mapping(source = "id.userId", target = "userId")
    List<EducationPostBookmarkDto> educationPostBookmarksToDtos(List<EducationPostBookmark> educationPostBookmarks);

    @Mapping(source = "postId", target = "id.postId")
    @Mapping(source = "userId", target = "id.userId")
    List<EducationPostBookmark> educationPostBookmarkDtosToEducationPostBookmarks(List<EducationPostBookmarkDto> educationPostBookmarkDtos);

    @Mapping(source = "id.postId", target = "postId")
    @Mapping(source = "id.userId", target = "userId")
    EducationPostBookmarkDto educationPostBookmarkToDto(EducationPostBookmark educationPostBookmark);

    @Mapping(source = "postId", target = "id.postId")
    @Mapping(source = "userId", target = "id.userId")
    EducationPostBookmark educationPostBookmarkDtoToEducationPostBookmark(EducationPostBookmarkDto educationPostBookmarkDto);
}

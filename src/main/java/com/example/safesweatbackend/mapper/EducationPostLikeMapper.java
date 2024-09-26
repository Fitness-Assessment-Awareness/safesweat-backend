package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.EducationPostLikeDto;
import com.example.safesweatbackend.model.entity.EducationPostLike;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationPostLikeMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "postId", target = "id.postId")
    @Mapping(source = "userId", target = "id.userId")
    void updateEducationPostLikeFromDto(EducationPostLikeDto educationPostLikeDto, @MappingTarget EducationPostLike educationPostLike);

    @Mapping(source = "id.postId", target = "postId")
    @Mapping(source = "id.userId", target = "userId")
    List<EducationPostLikeDto> educationPostLikesToDtos(List<EducationPostLike> educationPostLikes);

    @Mapping(source = "postId", target = "id.postId")
    @Mapping(source = "userId", target = "id.userId")
    List<EducationPostLike> educationPostLikeDtosToEducationPostLikes(List<EducationPostLikeDto> educationPostLikeDtos);

    @Mapping(source = "id.postId", target = "postId")
    @Mapping(source = "id.userId", target = "userId")
    EducationPostLikeDto educationPostLikeToDto(EducationPostLike educationPostLike);

    @Mapping(source = "postId", target = "id.postId")
    @Mapping(source = "userId", target = "id.userId")
    EducationPostLike educationPostLikeDtoToEducationPostLike(EducationPostLikeDto educationPostLikeDto);
}

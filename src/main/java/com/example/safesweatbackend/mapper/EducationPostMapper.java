package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.EducationPostDto;
import com.example.safesweatbackend.model.entity.EducationPost;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationPostMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEducationPostFromDto(EducationPostDto educationPostDto, @MappingTarget EducationPost educationPost);

    EducationPostDto educationPostToDto(EducationPost educationPost);

    EducationPost educationPostDtoToEducationPost(EducationPostDto educationPostDto);

    List<EducationPostDto> educationPostsToDtos(List<EducationPost> educationPosts);
}

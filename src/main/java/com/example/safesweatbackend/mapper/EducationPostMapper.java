package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.EducationPostDto;
import com.example.safesweatbackend.model.entity.EducationPost;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = EducationPostCategoryMapper.class)
public interface EducationPostMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEducationPostFromDto(EducationPostDto educationPostDto, @MappingTarget EducationPost educationPost);

    @Mapping(source = "category", target = "categoryDto")
    EducationPostDto educationPostToDto(EducationPost educationPost);

    EducationPost educationPostDtoToEducationPost(EducationPostDto educationPostDto);

    @Mapping(source = "category", target = "categoryDto")
    List<EducationPostDto> educationPostsToDtos(List<EducationPost> educationPosts);
}

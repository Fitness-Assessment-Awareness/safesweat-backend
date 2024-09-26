package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.EducationPostDto;
import com.example.safesweatbackend.model.entity.EducationPost;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EducationPostCategoryMapper.class, EducationPostLikeMapper.class, EducationPostBookmarkMapper.class})
public interface EducationPostMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "educationPostLikeDtos", target = "educationPostLikes")
    @Mapping(source = "educationPostBookmarkDtos", target = "educationPostBookmarks")
    void updateEducationPostFromDto(EducationPostDto educationPostDto, @MappingTarget EducationPost educationPost);

    @Mapping(source = "category", target = "categoryDto")
    @Mapping(source = "educationPostLikes", target = "educationPostLikeDtos")
    @Mapping(source = "educationPostBookmarks", target = "educationPostBookmarkDtos")
    EducationPostDto educationPostToDto(EducationPost educationPost);

    EducationPost educationPostDtoToEducationPost(EducationPostDto educationPostDto);

    @Mapping(source = "category", target = "categoryDto")
    @Mapping(source = "educationPostLikes", target = "educationPostLikeDtos")
    @Mapping(source = "educationPostBookmarks", target = "educationPostBookmarkDtos")
    List<EducationPostDto> educationPostsToDtos(List<EducationPost> educationPosts);
}

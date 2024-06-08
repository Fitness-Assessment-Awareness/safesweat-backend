package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.EducationPostCategoryDto;
import com.example.safesweatbackend.model.entity.EducationPostCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationPostCategoryMapper {

    List<EducationPostCategoryDto> educationPostCategoriesToDtos(List<EducationPostCategory> educationCategories);

    EducationPostCategoryDto educationPostCategoryToDto(EducationPostCategory educationCategory);
}

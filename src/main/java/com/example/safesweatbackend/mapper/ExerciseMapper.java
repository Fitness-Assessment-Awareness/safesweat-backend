package com.example.safesweatbackend.mapper;

import com.example.safesweatbackend.model.dto.ExerciseDto;
import com.example.safesweatbackend.model.entity.Exercise;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    List<ExerciseDto> exercisesToDtos(List<Exercise> exercises);

}

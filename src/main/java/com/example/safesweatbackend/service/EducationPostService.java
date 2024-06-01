package com.example.safesweatbackend.service;

import com.example.safesweatbackend.model.dto.EducationPostDto;

import java.util.List;
import java.util.UUID;

public interface EducationPostService {

    EducationPostDto create(EducationPostDto educationPostDTO);

    EducationPostDto get(UUID id);

    List<EducationPostDto> getAll();

    EducationPostDto update(EducationPostDto educationPostDTO);

    void delete(UUID id);
}

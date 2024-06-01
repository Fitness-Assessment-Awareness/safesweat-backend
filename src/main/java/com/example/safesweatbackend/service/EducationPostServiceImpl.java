package com.example.safesweatbackend.service;

import com.example.safesweatbackend.mapper.EducationPostMapper;
import com.example.safesweatbackend.model.dto.EducationPostDto;
import com.example.safesweatbackend.model.entity.EducationPost;
import com.example.safesweatbackend.repo.EducationPostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EducationPostServiceImpl implements EducationPostService {

    private final EducationPostRepo educationPostRepo;

    private final EducationPostMapper mapper;

    @Override
    public EducationPostDto create(EducationPostDto educationPostDTO) {
        if (educationPostRepo.findCategoryById(educationPostDTO.getCategoryId()) == null) {
            throw new IllegalArgumentException("Invalid Category ID");
        }
        EducationPost educationPost = mapper.educationPostDtoToEducationPost(educationPostDTO);
        Date currentDate = new Date();
        educationPost.setCreatedDate(currentDate);
        EducationPost educationPostCreated = educationPostRepo.save(educationPost);
        return mapper.educationPostToDto(educationPostCreated);
    }

    @Override
    public EducationPostDto get(UUID id) {
        EducationPost educationPost = educationPostRepo.findById(id).get();
        return mapper.educationPostToDto(educationPost);
    }

    @Override
    public List<EducationPostDto> getAll() {
        List<EducationPost> educationPosts = educationPostRepo.findAll();
        return mapper.educationPostsToDtos(educationPosts);
    }

    @Override
    public EducationPostDto update(EducationPostDto educationPostDTO) {
        if (educationPostRepo.findCategoryById(educationPostDTO.getCategoryId()) == null) {
            throw new IllegalArgumentException("Invalid Category ID");
        }
        UUID postId = educationPostDTO.getPostId();
        EducationPost educationPost = educationPostRepo.findById(postId).get();
        mapper.updateEducationPostFromDto(educationPostDTO, educationPost);
        educationPost.setLastUpdatedDate(new Date());
        EducationPost educationPostUpdated = educationPostRepo.save(educationPost);
        return mapper.educationPostToDto(educationPostUpdated);
    }

    @Override
    public void delete(UUID id) {
        educationPostRepo.deleteById(id);
    }
}

package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.model.dto.EducationPostCategoryDto;
import com.example.safesweatbackend.model.dto.EducationPostDto;
import com.example.safesweatbackend.service.EducationPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/education-post")
public record EducationPostController(EducationPostService educationPostService) {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/list")
    public ResponseEntity<List<EducationPostDto>> getEducationPosts() {
        return ResponseEntity.ok(
            educationPostService.getAll()
        );
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}")
    public ResponseEntity<EducationPostDto> getEducationPost(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                educationPostService.get(id)
        );
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    public ResponseEntity<EducationPostDto> createEducationPost(@RequestBody EducationPostDto educationPostDto) {
        System.out.println(educationPostDto);
        return ResponseEntity.ok(
                educationPostService.create(educationPostDto)
        );
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PatchMapping
    public ResponseEntity<EducationPostDto> updateEducationPost(@RequestBody EducationPostDto educationPostDto) {
        return ResponseEntity.ok(
                educationPostService.update(educationPostDto)
        );
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducationPost(@PathVariable("id") UUID id) {
        educationPostService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/categories")
    public ResponseEntity<List<EducationPostCategoryDto>> getEducationPostCategories() {
        return ResponseEntity.ok(
                educationPostService.getAllCategories()
        );
    }
}

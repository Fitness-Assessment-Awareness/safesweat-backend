package com.example.safesweatbackend.controller;

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

    @GetMapping("/list")
    public ResponseEntity<List<EducationPostDto>> getEducationPosts() {
        return ResponseEntity.ok(
            educationPostService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationPostDto> getEducationPost(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                educationPostService.get(id)
        );
    }

    @PostMapping
    public ResponseEntity<EducationPostDto> createEducationPost(@RequestBody EducationPostDto educationPostDto) {
        System.out.println(educationPostDto);
        return ResponseEntity.ok(
                educationPostService.create(educationPostDto)
        );
    }

    @PatchMapping
    public ResponseEntity<EducationPostDto> updateEducationPost(@RequestBody EducationPostDto educationPostDto) {
        return ResponseEntity.ok(
                educationPostService.update(educationPostDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducationPost(@PathVariable("id") UUID id) {
        educationPostService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

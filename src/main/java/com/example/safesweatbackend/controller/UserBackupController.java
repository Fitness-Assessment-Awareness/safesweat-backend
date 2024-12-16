package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.model.dto.backup.UserBackupDataDto;
import com.example.safesweatbackend.service.UserBackupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user-backup")
public record UserBackupController(UserBackupService userBackupService) {

    @GetMapping("/{userId}")
    public ResponseEntity<UserBackupDataDto> getUserBackupData(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(
                userBackupService.get(userId)
        );
    }

    @PostMapping
    public ResponseEntity<UserBackupDataDto> createUserBackupData(@RequestBody UserBackupDataDto userBackupDataDto) {
        return ResponseEntity.ok(
                userBackupService.create(userBackupDataDto)
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserBackupData(@PathVariable("userId") UUID userId) {
        userBackupService.delete(userId);
        return ResponseEntity.accepted().build();
    }
}

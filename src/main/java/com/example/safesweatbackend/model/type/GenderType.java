package com.example.safesweatbackend.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderType {
    MALE("Male"),
    FEMALE("Female");

    private final String label;

    public static boolean isValidGender(String gender) {
        for (GenderType type : GenderType.values()) {
            if (type.getLabel().equalsIgnoreCase(gender)) {
                return true;
            }
        }
        return false;
    }
}

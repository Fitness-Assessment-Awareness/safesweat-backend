package com.example.safesweatbackend.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FocusAreaType {
    FullBody("Full Body"),
    Arm("Arm"),
    Abs("Abs"),
    Butt("Butt"),
    Leg("Leg");

    private final String label;

    public static boolean isValidFocusArea(String focusArea) {
        for (FocusAreaType type : FocusAreaType.values()) {
            if (type.getLabel().equalsIgnoreCase(focusArea)) {
                return true;
            }
        }
        return false;
    }
}

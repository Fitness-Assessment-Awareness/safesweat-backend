package com.example.safesweatbackend.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HealthProblemType {
    HeartCondition("Heart Condition"),
    ChestPainWithPhysicalActivity("Chest Pain with Physical Activity"),
    ChestPainWithoutPhysicalActivity("Chest Pain without Physical Activity"),
    Dizziness("Dizziness"),
    BoneOrJointProblem("Bone/Joint Problem"),
    UnderBloodPressureDrugs("Under Blood Pressure Drugs");

    private final String label;

    public static boolean isValidHealthProblem(String healthProblem) {
        for (HealthProblemType type : HealthProblemType.values()) {
            if (type.getLabel().equalsIgnoreCase(healthProblem)) {
                return true;
            }
        }
        return false;
    }
}

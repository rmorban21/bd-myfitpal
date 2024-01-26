package main.models;

public enum ExperienceLevel {
    BRAND_NEW("Never played sports or followed a workout regimen consistently before"),
    BEGINNER("Played sports or followed a workout regimen before"),
    INTERMEDIATE("Previously committed to a consistent workout regimen for over 3 months " +
            "and made significant progress"),
    ADVANCED("Currently (or sometime in the past year for over 6 months consistently) " +
            "working out at least 4x/week"
            + " OR previous competitive athlete");

    private final String description;

    ExperienceLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
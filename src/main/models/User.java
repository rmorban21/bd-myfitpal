package main.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@DynamoDBTable(tableName = "users")

public class User {
    //TODO add fitness goal directly here
    private String userId;
    private String username;
    //TODO check how to secure password
    private String password;
    private String email;
    private FitnessGoal fitnessGoal;
    private int availability;
    private List<String> preferences;
    //TODO add experience level specifically
    private ExperienceLevel expLevel;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FitnessGoal getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(FitnessGoal fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public ExperienceLevel getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(ExperienceLevel expLevel) {
        this.expLevel = expLevel;
    }

}

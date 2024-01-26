package main.converter;

import main.models.ExperienceLevel;
import main.models.FitnessGoal;
import main.models.User;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelConverter {

    public static User convertToUser(Map<String, AttributeValue> item) {
        User user = new User();

        user.setUserId(item.get("userId").s());
        user.setUsername(item.get("username").s());
        user.setPassword(item.get("password").s());
        user.setEmail(item.get("email").s());
        String fitnessGoalString = item.get("fitnessGoal").s();
        user.setFitnessGoal(FitnessGoal.valueOf(fitnessGoalString));
        String experienceLevel = item.get("expLevel").s();
        user.setExpLevel(ExperienceLevel.valueOf(experienceLevel));
        user.setAvailability(Integer.parseInt(item.get("availability").n()));

        // Handle list of strings conversion for preferences
        List<String> preferences = item.get("preferences").l().stream()
                .map(AttributeValue::s)
                .collect(Collectors.toList());
        user.setPreferences(preferences);

        return user;
    }
    public static Map<String, AttributeValue> convertFromUser(User user) {
        Map<String, AttributeValue> item = new HashMap<>();

        item.put("userId", AttributeValue.builder().s(user.getUserId()).build());
        item.put("username", AttributeValue.builder().s(user.getUsername()).build());
        item.put("password", AttributeValue.builder().s(user.getPassword()).build());
        item.put("email", AttributeValue.builder().s(user.getEmail()).build());
        item.put("fitnessGoal", AttributeValue.builder().s(user.getFitnessGoal().toString()).build());
        item.put("availability", AttributeValue.builder().n(String.valueOf(user.getAvailability())).build());

        // Convert preferences list to DynamoDB format
        item.put("preferences", AttributeValue.builder().l(user.getPreferences().stream()
                .map(s -> AttributeValue.builder().s(s).build())
                .collect(Collectors.toList())).build());

        item.put("expLevel", AttributeValue.builder().s(user.getExpLevel().toString()).build());

        return item;
    }
}


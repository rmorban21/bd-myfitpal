package main.dependency;

import main.converter.ModelConverter;
import main.exceptions.DatabaseOperationException;
import main.exceptions.UserAlreadyExistsException;
import main.exceptions.UserNotFoundException;
import main.repositories.UserRepository;
import main.models.FitnessGoal;
import main.models.User;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import javax.inject.Inject;
import java.util.*;
import java.util.logging.Logger;

import static main.converter.ModelConverter.convertToUser;

public class UserRepositoryImpl implements UserRepository {

    private final DynamoDbClient dynamoDbClient;
    private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Inject
    public UserRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void createUser(User user) throws UserAlreadyExistsException, UserNotFoundException {
        //Check if user already exists
        User existingUser = findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        user.setUserId(UUID.randomUUID().toString());

        //Convert User obj to DynamoDb format
        Map<String, AttributeValue> item = ModelConverter.convertFromUser(user);

        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("MyFitPalUsers")
                .item(item)
                .build();

        dynamoDbClient.putItem(putItemRequest);

    }

    @Override
    public User findUserById(String userId) {
        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("MyFitPalUsers")
                .key(Collections.singletonMap("userId", AttributeValue.builder().s(userId)
                        .build())).build();
        GetItemResponse response = dynamoDbClient.getItem(getItemRequest);
        if(!response.hasItem()) {
            return null;
        }
        return convertToUser(response.item());
    }

    @Override
    public void updateUser(User user) {

        Map<String, AttributeValue> item = ModelConverter.convertFromUser(user);
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("MyFitPalUsers")
                .item(item)
                .build();
        dynamoDbClient.putItem(putItemRequest);

    }

    @Override
    public void deleteUser(String userId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(userId).build());

        DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder()
                .tableName("MyFitPalUsers")
                .key(key)
                .build();

        dynamoDbClient.deleteItem(deleteItemRequest);
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException, DatabaseOperationException {
        QueryRequest queryRequest = QueryRequest.builder()
                .tableName("MyFitPalUsers")
                .indexName("usernameIndex")
                .keyConditionExpression("username = :v_username")
                .expressionAttributeValues(Map.of(":v_username", AttributeValue.builder().s(username).build()))
                .build();

        try {
            QueryResponse queryResponse = dynamoDbClient.query(queryRequest);
            return queryResponse.items().stream()
                    .map(ModelConverter::convertToUser)
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("No User was found with the username: " + username));
        } catch (DynamoDbException e) {
            logger.severe("Error querying user by username: " + username + " - " + e.getMessage());
            throw new DatabaseOperationException("Error querying DynamoDB", e);
        }
    }

    @Override
    public void updateUserPreferences(String userId, List<String> preferences) {
        User user = findUserById(userId);
        if (user != null) {
            user.setPreferences(preferences);
            updateUser(user);
        }
    }

    @Override
    public void updateUserFitnessGoals(String userId, FitnessGoal fitnessGoal) {
        User user = findUserById(userId);
        if (user != null) {
            user.setFitnessGoal(fitnessGoal);
            updateUser(user);
        }
    }

    @Override
    public FitnessGoal getUserFitnessGoal(String userId) {
        User user = findUserById(userId);
        return (user != null) ? user.getFitnessGoal() : null;
    }

    @Override
    public List<String> getUserPreferences(String userId) {
        User user = findUserById(userId);
        return (user != null) ? user.getPreferences() : Collections.emptyList();
    }
}

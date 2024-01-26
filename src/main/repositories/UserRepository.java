package main.repositories;

import main.exceptions.UserAlreadyExistsException;
import main.exceptions.UserNotFoundException;
import main.models.FitnessGoal;
import main.models.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user) throws UserAlreadyExistsException, UserNotFoundException;
    User findUserById(String userId);
    void updateUser(User user);
    void deleteUser(String userId);
    User findUserByUsername(String username) throws UserNotFoundException;
    void updateUserPreferences(String userId, List<String> preferences);

    void updateUserFitnessGoals(String userId, FitnessGoal fitnessGoal);
    FitnessGoal getUserFitnessGoal(String userId);
    List<String> getUserPreferences(String userId);

}

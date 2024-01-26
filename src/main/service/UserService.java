package main.service;

import main.exceptions.UserAlreadyExistsException;
import main.exceptions.UserNotFoundException;
import main.models.FitnessGoal;
import main.models.User;
import main.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) throws UserAlreadyExistsException, UserNotFoundException {
        userRepository.createUser(user);
    }

    public User findUserById(String userId) {
        return userRepository.findUserById(userId);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }
    public void updateUserPreferences(String userId, List<String> preferences) {
        userRepository.updateUserPreferences(userId, preferences);
    }
    public void updateUserFitnessGoals(String userId, FitnessGoal fitnessGoal) {
        userRepository.updateUserFitnessGoals(userId, fitnessGoal);
    }
    public FitnessGoal getUserFitnessGoal(String userId) {
        return userRepository.getUserFitnessGoal(userId);
    }
    public List<String> getUserPreferences(String userId) {
        return userRepository.getUserPreferences(userId);
    }

}

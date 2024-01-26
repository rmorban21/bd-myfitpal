package main.controllers;

import main.exceptions.UserAlreadyExistsException;
import main.exceptions.UserNotFoundException;
import main.models.FitnessGoal;
import main.models.User;
import main.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) throws UserNotFoundException, UserAlreadyExistsException {
        // Validate username
        if (!isValidUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username must be 6-12 characters long and contain only letters and numbers.");
        }

        // Validate email
        if (!isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format.");
        }

        // Validate password
        if (!isValidPassword(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password must consist of 6-15 characters and include uppercase, lowercase, numbers, and !$* symbols.");
        }

        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    private boolean isValidUsername(String username) {
        if (username == null || username.length() < 6 || username.length() > 12) {
            return false;
        }
        return username.matches("[a-zA-Z0-9]+");
    }

    private boolean isValidEmail(String email) {
        // Use regex to validate email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Use regex to validate password
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!$*])(?=\\S+$).{6,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }


    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{userId}/preferences")
    public ResponseEntity<String> updateUserPreferences(@PathVariable String userId,
                                                        @RequestBody List<String> preferences) {
        userService.updateUserPreferences(userId, preferences);
        return ResponseEntity.ok("User preferences updated successfully");
    }

    @PutMapping("/{userId}/fitness-goal")
    public ResponseEntity<String> updateUserFitnessGoal(@PathVariable String userId,
                                                        @RequestBody FitnessGoal fitnessGoal) {
        userService.updateUserFitnessGoals(userId, fitnessGoal);
        return ResponseEntity.ok("User fitness goal updated successfully");
    }
}

package com.example.E_Commerce_MicroServices.controllers;
import com.example.E_Commerce_MicroServices.models.Category;
import com.example.E_Commerce_MicroServices.models.User;
import com.example.E_Commerce_MicroServices.services.UserService;
import com.example.E_Commerce_MicroServices.services.converters.UserDtoToUserConverter;
import com.example.E_Commerce_MicroServices.services.converters.UserToUserDtoConverter;
import com.example.E_Commerce_MicroServices.services.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserToUserDtoConverter userToUserDtoConverter;
    @Autowired
    private UserDtoToUserConverter userDtoToUserConverter;
    // Retrieve a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        UserDto userDto = userToUserDtoConverter.convert(user);
        return ResponseEntity.ok(userDto);
    }
    // Retrieve all users
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(userToUserDtoConverter::convert)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }
    // Create a new user
    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        User createdUser = userService.createUser(user);
        UserDto createdUserDto = userToUserDtoConverter.convert(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }
    // Update user details
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        User updatedUser = userService.update(userId, user);
        UserDto updatedUserDto = userToUserDtoConverter.convert(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }
    // Delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    // Check if a username exists
    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        boolean exists = userService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }
    // Check if an email exists
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
    // Login user
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");
        User user = userService.login(email, password);
        UserDto userDto = userToUserDtoConverter.convert(user);
        return ResponseEntity.ok(userDto);
    }
    // Change user password
    @PutMapping("/{userId}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> passwordDetails) {
        String oldPassword = passwordDetails.get("oldPassword");
        String newPassword = passwordDetails.get("newPassword");
        User user = userService.findUserById(userId);  // Fetch user
        userService.changePassword(user, oldPassword, newPassword);
        return ResponseEntity.ok().build();
    }
    // Get user interests (categories)
    @GetMapping("/{userId}/interests")
    public ResponseEntity<Set<Category>> getUserInterests(@PathVariable Long userId) {
        Set<Category> interests = userService.getInterests(userId);
        return ResponseEntity.ok(interests);
    }
}
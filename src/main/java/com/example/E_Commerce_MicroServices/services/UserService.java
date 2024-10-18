package com.example.E_Commerce_MicroServices.services;

import com.example.E_Commerce_MicroServices.models.Category;
import com.example.E_Commerce_MicroServices.repositories.UserRepository;
import com.example.E_Commerce_MicroServices.system.exceptions.ObjectNotFoundException;
import com.example.E_Commerce_MicroServices.system.exceptions.ValidationException;
import com.example.E_Commerce_MicroServices.system.utils.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.E_Commerce_MicroServices.models.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserValidator userValidator;
    public User createUser(User user) {
        // Encode password before saving in the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }
    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }
    public User findUserById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("user", id));
    }
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("user", username));
    }
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("user", email));
    }
    public User update(Long userId, User user) {
        // Find the user in the repository
        User foundUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        List<String> validationErrors = userValidator.validateUserInput(user, false, false);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors); // Custom exception for validation failures
        }
        // Update the necessary fields
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setCountry(user.getCountry());
        foundUser.setCity(user.getCity());
        foundUser.setStreet(user.getStreet());
        foundUser.setCreditLimit(user.getCreditLimit());
        foundUser.setBirthdate(user.getBirthdate());
        foundUser.setPhone(user.getPhone());
        /* update Rest of Attributes : Haroun */
        foundUser.setUsername(user.getUsername());
        foundUser.setEmail(user.getEmail());
        foundUser.clearCategories();
        foundUser.setCategories(user.getInterests());
        // Save the updated user
        return this.userRepository.save(foundUser);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changePassword(User user, String oldPassword, String newPassword) {
        List<String> validationErrors = userValidator.validateChangePassword(user, oldPassword, newPassword); // Call the validatePassword() // Call the validateUserInput
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors); // Custom exception for validation failures
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        this.userRepository.save(user);
    }
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
    // Login
    public User login(String email, String password) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User", email));
        // Use passwordEncoder to check the password against the stored hash
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ObjectNotFoundException("User", email);  // Password doesn't match, user unauthorized
        } else {
            return user;
        }
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public Set<Category> getInterests(Long userId) {
        return userRepository.findInterestsByUserId(userId);
    }
}
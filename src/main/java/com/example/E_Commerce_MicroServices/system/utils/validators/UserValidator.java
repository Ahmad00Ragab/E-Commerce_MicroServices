package com.example.E_Commerce_MicroServices.system.utils.validators;

import com.example.E_Commerce_MicroServices.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    @Autowired
    PasswordEncoder passwordEncoder;

    // Main method to validate user attributes based on required fields for each context
    public List<String> validateUserInput(User user, boolean isAddressRequired, boolean isRegistration) {
        List<String> errors = new ArrayList<>();

        // Custom validation for username, email, phone, password, and credit limit are no longer necessary
        // since these fields are already validated using annotations in the User class.

        // Validate address (optional for profile update, mandatory for orders)
        if (isAddressRequired && !validateAddress(user)) {
            errors.add("Invalid address: country, city, and street are required.");
        }

        return errors;
    }

    // Validation for address (this part is not handled by annotations)
    private boolean validateAddress(User user) {
        return !isNullOrEmpty(user.getCountry()) &&
                !isNullOrEmpty(user.getCity()) &&
                !isNullOrEmpty(user.getStreet());
    }

    // Validation for password change request
    public List<String> validateChangePassword(User user, String oldPassword, String newPassword) {
        List<String> errors = new ArrayList<>();

        if (oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            errors.add("Old password and new password cannot be empty.");
            return errors;
        }

        // Custom password matching logic (this cannot be handled by annotations)
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            errors.add("Old password is incorrect.");
        }

        // Password length validation is now handled by annotations in the User class
        if (!validatePassword(newPassword)) {
            errors.add("Invalid password: must be at least 6 characters long.");
        }

        return errors;
    }

    // Utility method for checking null or empty strings (for manual validations)
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Password validation (minimal complexity requirements can be customized if needed)
    private boolean validatePassword(String password) {
        return password.length() >= 6;  // Additional complexity can be added here if needed
    }
}
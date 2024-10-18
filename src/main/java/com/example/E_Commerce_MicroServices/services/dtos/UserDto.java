package com.example.E_Commerce_MicroServices.services.dtos;

import com.example.E_Commerce_MicroServices.models.User;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link User}
 */
public record UserDto(Long id,
        @Size(message = "Username must be between 3 and 50 characters", min = 3, max = 50) 
        @NotBlank(message = "Username is required") 
        String username,

        @Size(message = "Password must be at least 6 characters long", min = 6) 
        @NotBlank(message = "Password is required") 
        String password,

        @Size(message = "First name must be less than 50 characters", max = 50) 
        String firstName,

        @Size(message = "Last name must be less than 50 characters", max = 50) 
        String lastName,

        @Pattern(message = "Email must follow correct format (e.g., user@domain.com)", 
                 regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") 
        @Email(message = "Invalid email format") 
        @NotEmpty(message = "Email is required") 
        String email,

        @Size(message = "Country must be less than 100 characters", max = 100) 
        String country,

        @Size(message = "City must be less than 100 characters", max = 100) 
        String city,

        @Size(message = "Street must be less than 150 characters", max = 150) 
        String street, 

        @DecimalMin(value = "0.0", inclusive = false, message = "Credit limit must be a positive number") 
        BigDecimal creditLimit,

        @Past(message = "Birthdate must be in the past") 
        LocalDate birthdate,

        @Pattern(message = "Phone number must be between 10 and 15 digits, and may include a leading + for international numbers.", 
                 regexp = "^\\+?[0-9]{10,15}$") 
        String phone,

        LocalDate dateCreated,

        @PastOrPresent(message = "Last updated date must be in the past or present") 
        LocalDate lastUpdated
) implements Serializable {
}

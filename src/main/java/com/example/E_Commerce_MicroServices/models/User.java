package com.example.E_Commerce_MicroServices.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Column(name = "firstname")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;

    @Column(name = "lastname")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email must follow correct format (e.g., user@domain.com)")
    private String email;

    @Column(name = "country")
    @Size(max = 100, message = "Country must be less than 100 characters")
    private String country;

    @Column(name = "city")
    @Size(max = 100, message = "City must be less than 100 characters")
    private String city;

    @Column(name = "street")
    @Size(max = 150, message = "Street must be less than 150 characters")
    private String street;

    @Column(name = "credit_limit")
    @DecimalMin(value = "0.0", inclusive = false, message = "Credit limit must be a positive number")
    private BigDecimal creditLimit;

    @Column(name = "birthdate")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @Column(name="phone")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits, and may include a leading + for international numbers.")
    private String phone;

    @Column(name= "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name="last_updated", nullable = false)
    private LocalDate lastUpdated;

    
    // Initialize cartItems
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<CartItem> cartItems = new HashSet<>();

    // Initialize wishlist
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishlist = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    // @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // Gives Error in code 
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

//    @Column(name = "email_status")
//    private EmailStatus verificationCode;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDate.now();
    }


    public User(String username, String email, String password, LocalDate dateCreated, LocalDate lastUpdated) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.cartItems = new HashSet<>();  // Initialize cartItems
        this.wishlist = new HashSet<>();  // Initialize wishlist
        this.categories = new HashSet<>();  // Initialize categories
        this.orders = new ArrayList<>();
    }

    // Constructor to initialize required fields and collections
    public User(String username, String firstName, String lastName, String email, String password,
                String country, String city, String street, BigDecimal creditCardLimit,
                LocalDate birthdate, String phone, LocalDate dateCreated,
                LocalDate lastUpdated ) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.street = street;
        this.creditLimit = creditCardLimit;
        this.birthdate = birthdate;
        this.phone = phone;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.cartItems = new HashSet<>(); // Initialize cartItems
        this.wishlist = new HashSet<>();  // Initialize wishlist
        this.categories = new HashSet<>(); // Initialize categories
        this.orders = new ArrayList<>();

    }

    public Set<Category> getInterests() {
        return this.categories;
    }

    public void clearCategories() {
        this.categories.clear();
    }

    public void setCategories(Set<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
    }
}

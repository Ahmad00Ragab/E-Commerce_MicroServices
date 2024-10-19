package com.example.E_Commerce_MicroServices.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    // Initialize orderItems
    // @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    // @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY) /* when adding the order, add with it the order items, in order_items table */
    @JsonIgnore
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order(User user, BigDecimal totalPrice, LocalDateTime dateCreated) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;
        this.orderItems = new HashSet<>(); // Initialize orderItems
    }

    // Method to get order details as a string
    public String getOrderDetails() {
        return "Order ID: " + id + 
               ", Total Price: $" + totalPrice + 
               ", Date: " + dateCreated;
    }

}

package com.example.E_Commerce_MicroServices.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonManagedReference // Manage serialization of products
    private Set<Product> products = new HashSet<>();
}

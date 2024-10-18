package com.example.E_Commerce_MicroServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

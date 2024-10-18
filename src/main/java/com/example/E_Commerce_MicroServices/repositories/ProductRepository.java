package com.example.E_Commerce_MicroServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {
    List<Product> findByNameContainingIgnoreCase(String name);
}

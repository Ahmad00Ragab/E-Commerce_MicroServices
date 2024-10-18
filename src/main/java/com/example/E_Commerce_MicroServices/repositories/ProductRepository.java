package com.example.E_Commerce_MicroServices.repositories;

import com.example.E_Commerce_MicroServices.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

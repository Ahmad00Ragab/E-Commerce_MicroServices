package com.example.E_Commerce_MicroServices.repositories;

import com.example.E_Commerce_MicroServices.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.*;
import java.util.*;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<CategoryProjection> findAllBy();
}


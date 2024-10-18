package com.example.E_Commerce_MicroServices.repositories;

import com.example.E_Commerce_MicroServices.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("SELECT u.categories FROM User u WHERE u.id = :userId")
    Set<Category> findInterestsByUserId(@Param("userId") Long userId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

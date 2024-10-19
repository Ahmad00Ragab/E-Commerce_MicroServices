package com.example.E_Commerce_MicroServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.CartItem;
import com.example.E_Commerce_MicroServices.models.CartKey;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CartItemRepository extends JpaRepository<CartItem, CartKey> {
    List<CartItem> findByUserId(Long userId);

    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.product.id = :productId")
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
    void deleteAllByUserId(Long userId);

}

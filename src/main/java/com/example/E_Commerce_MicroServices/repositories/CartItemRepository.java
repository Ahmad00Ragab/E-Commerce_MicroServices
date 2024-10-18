package com.example.E_Commerce_MicroServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.CartItem;
import com.example.E_Commerce_MicroServices.models.CartKey;
import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, CartKey> {
    List<CartItem> findByUserId(Long userId);  

    CartItem findByUserIdAndProductId(Long userId, Long productId);

    void deleteAllByUserId(Long userId);
}

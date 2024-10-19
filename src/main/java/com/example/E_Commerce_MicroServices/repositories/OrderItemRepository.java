package com.example.E_Commerce_MicroServices.repositories;

import com.example.E_Commerce_MicroServices.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.OrderItem;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

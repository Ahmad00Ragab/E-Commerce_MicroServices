package com.example.E_Commerce_MicroServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_Commerce_MicroServices.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

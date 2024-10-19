package com.example.E_Commerce_MicroServices.services;

import com.example.E_Commerce_MicroServices.models.CartItem;
import com.example.E_Commerce_MicroServices.models.Product;
import com.example.E_Commerce_MicroServices.models.User;
import com.example.E_Commerce_MicroServices.repositories.CartItemRepository;
import com.example.E_Commerce_MicroServices.repositories.ProductRepository;
import com.example.E_Commerce_MicroServices.repositories.UserRepository;
import com.example.E_Commerce_MicroServices.services.dtos.AddToCartRequest;
import com.example.E_Commerce_MicroServices.system.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void deleteCartItem(Long userId, Long productId) {
        Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
       if(cartItem.isPresent()) {
           cartItemRepository.delete(cartItem.get());
       }else{
           throw new UserNotFoundException();
       }

    }


    public CartItem addProductToCart(AddToCartRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(request.userId(), request.productId());

        CartItem cartItem;
        if (existingCartItem.isPresent()) {
            cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.quantity());
        }

        return cartItemRepository.save(cartItem);
    }

}

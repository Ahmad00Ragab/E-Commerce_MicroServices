package com.example.E_Commerce_MicroServices.controllers;

import com.example.E_Commerce_MicroServices.models.CartItem;
import com.example.E_Commerce_MicroServices.services.CartItemService;
import com.example.E_Commerce_MicroServices.services.dtos.AddToCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        CartItem newCartItem = cartItemService.addCartItem(cartItem);
        return ResponseEntity.ok(newCartItem);
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItemsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cartItemService.getCartItemsByUserId(userId));
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long userId, @PathVariable Long productId) {
        cartItemService.deleteCartItem(userId, productId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/items")
    public ResponseEntity<CartItem> addProductToCart(@RequestBody AddToCartRequest request) {
        CartItem cartItem=cartItemService.addProductToCart(request);
        return ResponseEntity.ok(cartItem);
    }
    @GetMapping("/{userId}/products")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cartItems);
    }
}

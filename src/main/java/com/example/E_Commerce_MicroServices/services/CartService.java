package com.example.E_Commerce_MicroServices.services;

import com.example.E_Commerce_MicroServices.models.CartItem;
import com.example.E_Commerce_MicroServices.models.CartKey;
import com.example.E_Commerce_MicroServices.models.Product;
import com.example.E_Commerce_MicroServices.repositories.CartItemRepository;
import com.example.E_Commerce_MicroServices.repositories.ProductRepository;
import com.example.E_Commerce_MicroServices.repositories.UserRepository;
import com.example.E_Commerce_MicroServices.system.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public CartItem createCart(CartItem cart) {
        return cartItemRepository.save(cart);
    }

    public List<CartItem> findByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void deleteCart(CartKey id) {
        cartItemRepository.deleteById(id);
    }

    // 2. Add product to cart
    public void addProductToCart(Long userId, Long productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product", productId));

        if(product.getStock() == 0) {
            throw new ObjectNotFoundException("Product", productId);
        }

        Optional<CartItem> existingCartItem = cartItemRepository.findByUserId(userId).stream().filter(cartItem -> cartItem.getProductId().equals(productId)).findFirst();

        if(existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        }
        else{
            CartItem cartItem = new CartItem(userId, productId, quantity);
            cartItemRepository.save(cartItem);
        }
    }

    // 3. Update the quantity of an item in the cart
    public void updateQuantity(Long userId, Long productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if(quantity == 0) {
            cartItemRepository.deleteById(new CartKey(userId, productId));
        }

        CartItem cartItem = cartItemRepository.findById(new CartKey(userId, productId))
                .orElseThrow(() -> new ObjectNotFoundException("Cart item", new CartKey(userId, productId)));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    // 4. Remove an item from the cart
    public void removeItem(Long userId, Long productId) {
        List<CartItem> cart = cartItemRepository.findByUserId(userId);
        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                cartItemRepository.deleteById(new CartKey(userId, productId));
                break;
            }
        }
    }

    // 5. Clear the cart
    public void clearCart(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }
        cartItemRepository.deleteAllByUserId(userId);
    }

    public CartItem findCartItem(Long userId, Long productId) {
        return cartItemRepository.findById(new CartKey(userId, productId))
                .orElseThrow(() -> new ObjectNotFoundException("Cart item", new CartKey(userId, productId)));
    }
}
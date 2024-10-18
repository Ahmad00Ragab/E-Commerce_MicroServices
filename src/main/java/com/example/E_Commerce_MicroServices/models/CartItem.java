package com.example.E_Commerce_MicroServices.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
public class CartItem {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name="userId", column=@Column(name="user_id")),
        @AttributeOverride(name="productId", column=@Column(name="product_id"))
    })
    private CartKey cartId;

    @Column(nullable = false)
    private int quantity;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference // Avoids recursion while serializing
    private User user;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @JsonManagedReference // Ensures proper serialization in Product
    private Product product;

    public CartItem(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.cartId = new CartKey(user.getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CartItem cart = (CartItem) obj;
        return quantity == cart.quantity && Objects.equals(cartId, cart.cartId);
    }
}

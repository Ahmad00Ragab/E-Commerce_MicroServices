package com.example.E_Commerce_MicroServices.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartKey implements Serializable {

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "product_id", nullable = false)
    Long productId;

}
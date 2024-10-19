package com.example.E_Commerce_MicroServices.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product Not Found");
    }
}

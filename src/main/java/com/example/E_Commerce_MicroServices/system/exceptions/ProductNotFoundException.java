package com.example.E_Commerce_MicroServices.system.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product not found");
    }
}

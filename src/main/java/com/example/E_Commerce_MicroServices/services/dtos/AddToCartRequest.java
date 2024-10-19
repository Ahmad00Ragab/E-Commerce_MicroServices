package com.example.E_Commerce_MicroServices.services.dtos;

public record AddToCartRequest (
     Long userId,
     Long productId,
     int quantity
){

}
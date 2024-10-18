package com.example.E_Commerce_MicroServices.system.exceptions;

public class CategoryNotFoundException extends  RuntimeException{
    public CategoryNotFoundException(){
        super("Category not found");
    }
}

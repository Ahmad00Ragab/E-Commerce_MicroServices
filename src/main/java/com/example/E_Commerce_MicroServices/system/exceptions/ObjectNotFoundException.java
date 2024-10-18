package com.example.E_Commerce_MicroServices.system.exceptions;

import com.example.E_Commerce_MicroServices.models.CartKey;

public class ObjectNotFoundException extends RuntimeException {

  
    public ObjectNotFoundException(String objectName, Long id) {
        super("Could not find " + objectName + " with Id " + id);
    }

    public ObjectNotFoundException(String objectName) {
        super("Could not find " + objectName);
    }

    public ObjectNotFoundException(String objectName, String id) {
        super("Could not find " + objectName + " with Id " + id);
    }

    public ObjectNotFoundException(String objectName, CartKey id) {
        super("Could not find " + objectName + " with Id " + id);
    }

    public ObjectNotFoundException(String objectName, Long id, Throwable cause) {
        super("Could not find " + objectName + " with Id " + id, cause);
    }

    public ObjectNotFoundException(String objectName, String id, Throwable cause) {
        super("Could not find " + objectName + " with Id " + id, cause);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
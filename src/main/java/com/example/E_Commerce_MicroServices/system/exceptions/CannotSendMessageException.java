package com.example.E_Commerce_MicroServices.system.exceptions;

public class CannotSendMessageException extends RuntimeException{
   public CannotSendMessageException(){
        super("Can't send message to this Email Address");
    }
}

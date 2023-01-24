package com.example.jaz.exceptions;

public class InvalidServiceException extends RuntimeException{
    public InvalidServiceException(String message){
        super(message);
    }
}

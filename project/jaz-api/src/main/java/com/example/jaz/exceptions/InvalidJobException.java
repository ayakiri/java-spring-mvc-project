package com.example.jaz.exceptions;

public class InvalidJobException extends RuntimeException{
    public InvalidJobException (String message){
        super(message);
    }
}

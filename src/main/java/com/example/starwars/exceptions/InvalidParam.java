package com.example.starwars.exceptions;
public class InvalidParam extends RuntimeException{
    public InvalidParam(String message){
        super(message);
    }
}
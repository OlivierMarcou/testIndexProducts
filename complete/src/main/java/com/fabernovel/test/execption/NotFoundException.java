package com.fabernovel.test.execption;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}
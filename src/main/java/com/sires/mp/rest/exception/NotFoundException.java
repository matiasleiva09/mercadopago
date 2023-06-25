package com.sires.mp.rest.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String leyend){
        super(leyend);
    }
}

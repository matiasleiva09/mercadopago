package com.sires.mp.rest.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String leyend){
        super(leyend);
    }
}

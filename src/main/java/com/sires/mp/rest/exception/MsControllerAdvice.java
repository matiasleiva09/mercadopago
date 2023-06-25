package com.sires.mp.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class MsControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest webRequest){
        return handler(ex,webRequest,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest webRequest){
        return handler(ex,webRequest,HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handler(RuntimeException ex,WebRequest webRequest,HttpStatus httpStatus){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("timestamp",new Date());
        data.put("message",ex.getMessage());
        return new ResponseEntity<>(data, httpStatus);
    }
}

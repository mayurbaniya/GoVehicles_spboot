package com.govehicle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralizeExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> Exceptionhandler(Exception e){

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception: Internal Server Error");
    }
}

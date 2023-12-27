package com.govehicle.controller.mailcontrollers;

public class InvalidMailException extends  Exception{

    public InvalidMailException(String message){
        super(message);
    }
}

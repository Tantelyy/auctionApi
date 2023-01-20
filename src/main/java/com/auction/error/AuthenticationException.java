package com.auction.error;

public class AuthenticationException extends Exception{
    private String message;
    public AuthenticationException(String message){
        super(message);
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}

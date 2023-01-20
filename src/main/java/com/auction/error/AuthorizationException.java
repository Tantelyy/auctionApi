package com.auction.error;

public class AuthorizationException extends Exception{
    private String message;
    public AuthorizationException(String message){
        super(message);
        this.message=message;
    }

    public String getMessage(){
        return this.message;
    }
}

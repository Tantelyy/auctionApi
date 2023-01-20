package com.auction.error;

public class UserNotFoundException extends Exception{
    private String message;
    public UserNotFoundException(String message){
        super(message);
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}

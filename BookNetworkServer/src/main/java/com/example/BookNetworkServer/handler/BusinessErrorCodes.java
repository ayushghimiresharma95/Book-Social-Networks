package com.example.BookNetworkServer.handler;

import org.springframework.http.HttpStatus;


import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import lombok.Getter;

@Getter
public enum BusinessErrorCodes {

    NO_CODE(0,NOT_IMPLEMENTED,"NO_CODE") ,
    INCORRECT_CORRECT_PASSWORD(300,BAD_REQUEST,"THE PASSWORD IS INCORRECT") ,
    NEW_PASSWORD_DOES_NOT_MATCH(301,BAD_REQUEST,"THE NEW PASSWORD DOES NOT MATCH"),
    ACCOUNT_LOCKED(302,FORBIDDEN,"USER ACCOUNT IS LOCKED") ,
    ACCOUNT_DISABLED(303,FORBIDDEN,"USER ACCOUNT IS DISABLED"),
    BAD_CREDENTIALS(304,FORBIDDEN,"EMAIL OR PASSWORD DOES NOT MATCH") ;

    
    private final Integer code ;

    
    private final HttpStatus status ;

    
    private final String description ;

    
    BusinessErrorCodes(int code,HttpStatus status,String description){
        this.code  = code ;
        this.status = status ;
        this.description = description ;
    }
    
}

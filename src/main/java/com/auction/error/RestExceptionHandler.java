package com.auction.error;

import java.util.HashMap;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auction.exception.SetterValueWrongException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SetterValueWrongException.class)
    protected ResponseEntity<Object> handleSetterWrongValue(SetterValueWrongException ex){
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    protected ResponseEntity<Object> handleAuthorizationDenied(AuthorizationException ex){
        return buildResponseEntity(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleFailedAuthentication(AuthenticationException ex){
        return buildResponseEntity(ex, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundUser(UserNotFoundException ex){
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, HttpStatus status) {
        ApiError apiError = new ApiError(status);
        apiError.setMessage(ex.getMessage());
        HashMap<String, ApiError> wrapper = new HashMap<>();
        wrapper.put("error", apiError);
        return new ResponseEntity<>(wrapper, status) ;
    }
}

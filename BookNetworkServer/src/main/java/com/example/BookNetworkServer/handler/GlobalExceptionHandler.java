package com.example.BookNetworkServer.handler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.hibernate.jdbc.Expectation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.mail.MessagingException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exp) {

        return ResponseEntity.status(UNAUTHORIZED).body(

        ExceptionResponse.builder().
            buinessErrorCode(BusinessErrorCodes.ACCOUNT_LOCKED.getCode()).
            buinessErrorDescription(BusinessErrorCodes.ACCOUNT_LOCKED.getDescription())
            .error(exp.getMessage())
            .build());

    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exp){
        return ResponseEntity.status(UNAUTHORIZED).body(
            ExceptionResponse.builder().
            buinessErrorCode(BusinessErrorCodes.ACCOUNT_DISABLED.getCode()).
            buinessErrorDescription(BusinessErrorCodes.ACCOUNT_DISABLED.getDescription()).
            error(exp.getMessage()).
            build()
        ) ;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp){
        return ResponseEntity.status(UNAUTHORIZED).body(
            ExceptionResponse.builder().
            buinessErrorCode(BusinessErrorCodes.INCORRECT_CORRECT_PASSWORD.getCode()).
            buinessErrorDescription(BusinessErrorCodes.INCORRECT_CORRECT_PASSWORD.getDescription()).
            build()
        ) ;
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exp){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
            ExceptionResponse
            .builder()
            .error(exp.getMessage())
            .build()
        ) ;
    }

}

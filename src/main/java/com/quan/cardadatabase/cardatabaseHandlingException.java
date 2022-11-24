package com.quan.cardadatabase;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.quan.cardadatabase.Exception.EntityNotFoundException;
import com.quan.cardadatabase.Exception.ErrorResponse;

@ControllerAdvice
public class cardatabaseHandlingException {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), ex);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), ex);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}

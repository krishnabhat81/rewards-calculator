package com.interview.rewards.calculator.controller.advice;

import com.interview.rewards.calculator.exception.InternalServerErrorException;
import com.interview.rewards.calculator.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**

 Global exception handler for handling specific types of exceptions.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**

     Handles the NotFoundException and returns a 404 Not Found response with the exception message.
     @param ex the NotFoundException to be handled.
     @return a ResponseEntity with the exception message and a 404 Not Found status code.
     */
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**

     Handles the InternalServerErrorException and returns a 500 Internal Server Error response with the exception message.
     @param ex the InternalServerErrorException to be handled.
     @return a ResponseEntity with the exception message and a 500 Internal Server Error status code.
     */
    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<String> handleInternalServerErrorException(InternalServerErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
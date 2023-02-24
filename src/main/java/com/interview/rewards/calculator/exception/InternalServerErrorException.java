package com.interview.rewards.calculator.exception;

/**

 This exception class represents an internal server error, which is thrown when an unexpected error
 occurs during the execution of the application.
 It extends the base Exception class and adds a constructor that takes a message and an exception object
 that caused the error. This allows for better logging and debugging of the error.
 */
public class InternalServerErrorException extends Exception {
    public InternalServerErrorException(String message, Exception e) {
        super(message, e);
    }
}

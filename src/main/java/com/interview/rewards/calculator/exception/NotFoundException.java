package com.interview.rewards.calculator.exception;

/**

 This exception class represents a resource not found error, which is thrown when the requested resource
 is not found in the system.
 It extends the base Exception class and adds a constructor that takes a message as a parameter.
 */
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}

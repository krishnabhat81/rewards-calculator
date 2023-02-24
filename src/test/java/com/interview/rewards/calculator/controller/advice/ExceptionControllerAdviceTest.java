package com.interview.rewards.calculator.controller.advice;

import com.interview.rewards.calculator.exception.InternalServerErrorException;
import com.interview.rewards.calculator.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExceptionControllerAdviceTest {

    @InjectMocks
    private ExceptionControllerAdvice exceptionControllerAdvice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleNotFoundException_shouldReturnNotFoundResponse() {
        NotFoundException notFoundException = new NotFoundException("Not found exception message");
        ResponseEntity<String> responseEntity = exceptionControllerAdvice.handleNotFoundException(notFoundException);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Not found exception message", responseEntity.getBody());
    }

    @Test
    public void handleInternalServerErrorException_shouldReturnInternalServerErrorResponse() {
        InternalServerErrorException internalServerErrorException = new InternalServerErrorException("Internal server error exception message", new Exception());
        ResponseEntity<String> responseEntity = exceptionControllerAdvice.handleInternalServerErrorException(internalServerErrorException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error exception message", responseEntity.getBody());
    }
}
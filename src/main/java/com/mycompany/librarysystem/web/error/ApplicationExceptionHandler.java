package com.mycompany.librarysystem.web.error;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        logger.error("Resource not found - URI: {}, Method: {}", request.getRequestURI(), request.getMethod(), ex);
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionResponse handleUnexpectedMethod(NotFoundException ex, HttpServletRequest request) {
        logger.error("Request method not allowed - URI: {}, Method: {}", request.getRequestURI(), request.getMethod(), ex);
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleGeneralExceptions(Exception ex, HttpServletRequest request) {
        logger.error("Unexpected error - URI: {}, Method: {}", request.getRequestURI(), request.getMethod(), ex);
        if (ex instanceof HttpClientErrorException) {
            return ResponseEntity.status(((HttpClientErrorException) ex).getStatusCode()).body(new ExceptionResponse(
                    ((HttpClientErrorException) ex).getStatusCode().value(),
                    ex.getMessage()
            ));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new ExceptionResponse("An unexpected error occurred."));
    }
}
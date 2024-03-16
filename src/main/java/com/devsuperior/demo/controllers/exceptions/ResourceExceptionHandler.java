package com.devsuperior.demo.controllers.exceptions;

import com.devsuperior.demo.service.exceptions.DatabaseException;
import com.devsuperior.demo.service.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Void> resourceNotFoundException(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Void> databaseViolationException(){
        return ResponseEntity.badRequest().build();
    }
}

package com.infinity.EmployeeManagement.Controller;

import com.infinity.EmployeeManagement.Exceptions.EmployeeNotFoundException;
import com.infinity.EmployeeManagement.Exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handelEmployeeNotFoundException(EmployeeNotFoundException e){
        ErrorResponse response=new
                ErrorResponse(LocalDateTime.now(),e.getMessage(),"Employee not found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}

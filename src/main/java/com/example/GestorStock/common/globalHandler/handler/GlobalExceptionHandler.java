package com.example.GestorStock.common.globalHandler.handler;

import com.example.GestorStock.common.globalHandler.customException.BusinessRuleException;
import com.example.GestorStock.common.globalHandler.customException.ResourceNotFoundException;
import com.example.GestorStock.common.globalHandler.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ERROR 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handlerValidationApi(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " +e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErrorResponseDto response = buildError(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // ERROR 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerResourceNotFound(ResourceNotFoundException ex){
        ErrorResponseDto response = buildError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // ERROR 409
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponseDto> handlerBusinessRule(BusinessRuleException ex){
        ErrorResponseDto response = buildError(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    private ErrorResponseDto buildError(HttpStatus status, String message){
        return ErrorResponseDto.builder()
                .message(message)
                .localDateTime(LocalDateTime.now())
                .status(status.value())
                .build();
    }
}

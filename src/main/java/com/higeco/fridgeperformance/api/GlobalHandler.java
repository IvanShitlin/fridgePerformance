package com.higeco.fridgeperformance.api;

import com.higeco.fridgeperformance.service.InvalidDateException;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDTO handleException(Exception ex) {
        return new ErrorDTO(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDTO handleRuntimeException(RuntimeException ex) {
        return new ErrorDTO(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InvalidDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDTO handleRuntimeException(InvalidDateException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
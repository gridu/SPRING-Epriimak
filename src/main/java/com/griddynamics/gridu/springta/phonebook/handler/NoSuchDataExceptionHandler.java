package com.griddynamics.gridu.springta.phonebook.handler;

import com.griddynamics.gridu.springta.phonebook.exception.NoSuchDataException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class NoSuchDataExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchDataException.class)
    public ResponseEntity<Object> handleNoSuchDataException(Exception e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", INTERNAL_SERVER_ERROR);
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, INTERNAL_SERVER_ERROR);
    }
}

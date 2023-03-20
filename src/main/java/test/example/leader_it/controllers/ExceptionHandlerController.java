package test.example.leader_it.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.example.leader_it.dtos.responses.ErrorResponse;
import test.example.leader_it.exceptions.bad_request_exceptions.BadRequestException;
import test.example.leader_it.exceptions.not_found_exceptions.NotFoundException;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> invalidData(BadRequestException e) {
        logger.warn("Exception with message: {} - was occurred", e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), new Date().getTime()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundData(NotFoundException e) {
        logger.warn("Exception with message: {} - was occurred", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage(), new Date().getTime()));
    }
}

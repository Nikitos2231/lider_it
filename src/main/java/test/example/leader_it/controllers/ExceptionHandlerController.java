package test.example.leader_it.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.example.leader_it.dtos.responses.ErrorResponse;
import test.example.leader_it.exceptions.InvalidDataForTeamException;
import test.example.leader_it.exceptions.PlayerFilterRequestException;
import test.example.leader_it.exceptions.TeamFilterRequestException;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(TeamFilterRequestException.class)
    public ResponseEntity<ErrorResponse> invalidFilterRequest(TeamFilterRequestException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), new Date().getTime()));
    }

    @ExceptionHandler(InvalidDataForTeamException.class)
    public ResponseEntity<ErrorResponse> invalidDataInTeam(InvalidDataForTeamException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), new Date().getTime()));
    }

    @ExceptionHandler(PlayerFilterRequestException.class)
    public ResponseEntity<ErrorResponse> invalidPlayerFilter(PlayerFilterRequestException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), new Date().getTime()));
    }


}

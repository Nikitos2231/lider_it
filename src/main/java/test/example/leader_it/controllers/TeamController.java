package test.example.leader_it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.example.leader_it.dtos.TeamDTO;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.exceptions.TeamFilterRequestException;
import test.example.leader_it.services.TeamService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    public List<TeamDTO> getAllTeams(@RequestBody(required = false) @Valid TeamFilterRequest request, BindingResult bindingResult) throws TeamFilterRequestException {
        if (bindingResult.hasErrors()) {
            throw new TeamFilterRequestException(
                    bindingResult.getFieldErrors().stream()
                            .map(e -> e.getDefaultMessage().concat("; ")).collect(Collectors.joining()));
        }
        return teamService.getAllTeams(request == null ? new TeamFilterRequest() : request);
    }

}

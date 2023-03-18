package test.example.leader_it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.example.leader_it.dtos.TeamDTO;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.exceptions.InvalidDataForTeamException;
import test.example.leader_it.exceptions.TeamFilterRequestException;
import test.example.leader_it.services.TeamService;
import test.example.leader_it.util.BindingResultFiller;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    public List<TeamDTO> getAllTeams(@RequestBody(required = false) @Valid TeamFilterRequest request, BindingResult bindingResult) throws TeamFilterRequestException {
        if (bindingResult.hasErrors()) {
            throw new TeamFilterRequestException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        return teamService.getAllTeams(request == null ? new TeamFilterRequest() : request);
    }

    @PostMapping("/teams")
    public ResponseEntity<Void> saveTeam(@RequestBody @Valid TeamDTO teamDTO, BindingResult bindingResult) throws InvalidDataForTeamException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataForTeamException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        teamService.saveTeam(teamDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

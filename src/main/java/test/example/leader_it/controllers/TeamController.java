package test.example.leader_it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import test.example.leader_it.dtos.PlayerDTO;
import test.example.leader_it.dtos.TeamDTO;
import test.example.leader_it.dtos.requests.PlayerFilterRequest;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.exceptions.InvalidDataForTeamException;
import test.example.leader_it.exceptions.PlayerFilterRequestException;
import test.example.leader_it.exceptions.TeamFilterRequestException;
import test.example.leader_it.exceptions.TeamNotFoundException;
import test.example.leader_it.services.PlayerService;
import test.example.leader_it.services.TeamService;
import test.example.leader_it.util.BindingResultFiller;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams(@RequestBody(required = false) @Valid TeamFilterRequest request, BindingResult bindingResult) throws TeamFilterRequestException {
        if (bindingResult.hasErrors()) {
            throw new TeamFilterRequestException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        return ResponseEntity.ok(teamService.getAllTeams(request == null ? new TeamFilterRequest() : request));
    }

    @PostMapping
    public ResponseEntity<Void> saveTeam(@RequestBody @Valid TeamDTO teamDTO, BindingResult bindingResult) throws InvalidDataForTeamException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataForTeamException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        teamService.saveTeam(teamDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{team_id}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(
            @PathVariable("team_id") long id,
            @RequestBody(required = false) @Valid PlayerFilterRequest request,
            BindingResult bindingResult) throws PlayerFilterRequestException {

        if (bindingResult.hasErrors()) {
            throw new PlayerFilterRequestException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        return ResponseEntity.ok(playerService.getPlayersByTeam(id, request == null ? new PlayerFilterRequest() : request));
    }

    @DeleteMapping("/{team_id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("team_id") long id) throws TeamNotFoundException {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

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
import test.example.leader_it.exceptions.*;
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

    @PostMapping("/{team_id}/players")
    public ResponseEntity<Void> addPlayer(@PathVariable("team_id") long id,
                                          @RequestBody @Valid PlayerDTO playerDTO,
                                          BindingResult bindingResult) throws InvalidDataForPlayerException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataForPlayerException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        playerService.savePlayer(playerDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{team_id}/players/{player_id}")
    public ResponseEntity<PlayerDTO> getPlayerInTeam(@PathVariable("team_id") long teamId,
                                                     @PathVariable("player_id") long playerId) {
        return ResponseEntity.ok(playerService.getPlayerInTeam(teamId, playerId));
    }

    @DeleteMapping("/{team_id}/players/{player_id}")
    public ResponseEntity<Void> deletePlayerInTeam(@PathVariable("team_id") long teamId,
                                                   @PathVariable("player_id") long playerId) throws PlayerNotFoundException {
        playerService.deletePlayerInTeam(teamId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}")
    public ResponseEntity<Void> updateTeam(@PathVariable("team_id") long id,
                                           @RequestBody @Valid TeamDTO teamDTO,
                                           BindingResult bindingResult) throws TeamNotFoundException, InvalidDataForTeamException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataForTeamException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        teamService.updateTeam(teamDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}/players/{player_id}")
    public ResponseEntity<Void> updatePLayerInTeam(@PathVariable("team_id") long teamId,
                                                   @PathVariable("player_id") long playerId,
                                                   @RequestBody @Valid PlayerDTO playerDTO,
                                                   BindingResult bindingResult) throws InvalidDataForPlayerException, PlayerNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataForPlayerException(BindingResultFiller.fillBindingResult(bindingResult));
        }
        playerService.updatePlayerInTeam(playerDTO, teamId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}/players/{player_id}/transferTo/{new_team_id}")
    public ResponseEntity<Void> transferPlayer(@PathVariable("team_id") long teamId,
                                               @PathVariable("player_id") long playerId,
                                               @PathVariable("new_team_id") long newTeamId) throws PlayerNotFoundException, TeamNotFoundException {
        playerService.transferPlayerInOtherTeam(playerId, teamId, newTeamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

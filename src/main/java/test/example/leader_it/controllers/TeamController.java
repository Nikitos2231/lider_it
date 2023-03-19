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
import test.example.leader_it.exceptions.bad_request_exceptions.BadRequestException;
import test.example.leader_it.exceptions.not_found_exceptions.NotFoundException;
import test.example.leader_it.services.PlayerService;
import test.example.leader_it.services.TeamService;
import test.example.leader_it.util.RequestValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final RequestValidator requestValidator;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams(@RequestBody(required = false) @Valid TeamFilterRequest request,
                                                     BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateRequest(bindingResult);
        return ResponseEntity.ok(teamService.getAllTeams(request == null ? new TeamFilterRequest() : request));
    }

    @PostMapping
    public ResponseEntity<Void> saveTeam(@RequestBody(required = false) @Valid TeamDTO teamDTO,
                                         BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, teamDTO);
        teamService.saveTeam(teamDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{team_id}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(@PathVariable("team_id") long id,
                                                            @RequestBody(required = false) @Valid PlayerFilterRequest request,
                                                            BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateRequest(bindingResult);
        return ResponseEntity.ok(playerService.getPlayersByTeam(id, request == null ? new PlayerFilterRequest() : request));
    }

    @DeleteMapping("/{team_id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("team_id") long id) throws NotFoundException {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{team_id}/players")
    public ResponseEntity<Void> addPlayer(@PathVariable("team_id") long id,
                                          @RequestBody(required = false) @Valid PlayerDTO playerDTO,
                                          BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, playerDTO);
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
                                                   @PathVariable("player_id") long playerId) throws NotFoundException {
        playerService.deletePlayerInTeam(teamId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}")
    public ResponseEntity<Void> updateTeam(@PathVariable("team_id") long id,
                                           @RequestBody(required = false) @Valid TeamDTO teamDTO,
                                           BindingResult bindingResult) throws NotFoundException, BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, teamDTO);
        teamService.updateTeam(teamDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}/players/{player_id}")
    public ResponseEntity<Void> updatePLayerInTeam(@PathVariable("team_id") long teamId,
                                                   @PathVariable("player_id") long playerId,
                                                   @RequestBody(required = false) @Valid PlayerDTO playerDTO,
                                                   BindingResult bindingResult) throws NotFoundException, BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, playerDTO);
        playerService.updatePlayerInTeam(playerDTO, teamId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}/players/{player_id}/transfer-to/{new_team_id}")
    public ResponseEntity<Void> transferPlayer(@PathVariable("team_id") long teamId,
                                               @PathVariable("player_id") long playerId,
                                               @PathVariable("new_team_id") long newTeamId) throws NotFoundException {
        playerService.transferPlayerInOtherTeam(playerId, teamId, newTeamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

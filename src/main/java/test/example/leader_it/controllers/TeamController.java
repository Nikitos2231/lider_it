package test.example.leader_it.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(TeamController.class);
    private final TeamService teamService;
    private final PlayerService playerService;
    private final RequestValidator requestValidator;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams(@RequestBody(required = false) @Valid TeamFilterRequest request,
                                                     BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateRequest(bindingResult);
        logger.info("User perform searching with parameters: {}", request);
        return ResponseEntity.ok(teamService.getAllTeams(request == null ? new TeamFilterRequest() : request));
    }

    @PostMapping
    public ResponseEntity<Void> saveTeam(@RequestBody(required = false) @Valid TeamDTO teamDTO,
                                         BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, teamDTO);
        teamService.saveTeam(teamDTO);
        logger.info("Saved new team with name: {}", teamDTO.getTeamName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{team_id}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(@PathVariable("team_id") long id,
                                                            @RequestBody(required = false) @Valid PlayerFilterRequest request,
                                                            BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateRequest(bindingResult);
        logger.info("User perform searching players by team with id: {}", id);
        return ResponseEntity.ok(playerService.getPlayersByTeam(id, request == null ? new PlayerFilterRequest() : request));
    }

    @DeleteMapping("/{team_id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("team_id") long id) throws NotFoundException {
        teamService.deleteTeam(id);
        logger.info("Team with id: {} - was deleted", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{team_id}/players")
    public ResponseEntity<Void> addPlayer(@PathVariable("team_id") long id,
                                          @RequestBody(required = false) @Valid PlayerDTO playerDTO,
                                          BindingResult bindingResult) throws BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, playerDTO);
        playerService.savePlayer(playerDTO, id);
        logger.info("Player with surname: {} - was added into the team with id: {}", playerDTO.getSurname(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{team_id}/players/{player_id}")
    public ResponseEntity<PlayerDTO> getPlayerInTeam(@PathVariable("team_id") long teamId,
                                                     @PathVariable("player_id") long playerId) {
        logger.info("User perform searching player with id: {}, into the team with id: {}", playerId, teamId);
        return ResponseEntity.ok(playerService.getPlayerInTeam(teamId, playerId));
    }

    @DeleteMapping("/{team_id}/players/{player_id}")
    public ResponseEntity<Void> deletePlayerInTeam(@PathVariable("team_id") long teamId,
                                                   @PathVariable("player_id") long playerId) throws NotFoundException {
        playerService.deletePlayerInTeam(teamId, playerId);
        logger.info("Player with id: {}, into the team with id: {} - was deleted", playerId, teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}")
    public ResponseEntity<Void> updateTeam(@PathVariable("team_id") long id,
                                           @RequestBody(required = false) @Valid TeamDTO teamDTO,
                                           BindingResult bindingResult) throws NotFoundException, BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, teamDTO);
        teamService.updateTeam(teamDTO, id);
        logger.info("Team with id: {} - was updated", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}/players/{player_id}")
    public ResponseEntity<Void> updatePLayerInTeam(@PathVariable("team_id") long teamId,
                                                   @PathVariable("player_id") long playerId,
                                                   @RequestBody(required = false) @Valid PlayerDTO playerDTO,
                                                   BindingResult bindingResult) throws NotFoundException, BadRequestException {
        requestValidator.validateEntityRequest(bindingResult, playerDTO);
        playerService.updatePlayerInTeam(playerDTO, teamId, playerId);
        logger.info("Player with id: {}, that was member of the team with id: {} - was updated", playerId, teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{team_id}/players/{player_id}/transfer-to/{new_team_id}")
    public ResponseEntity<Void> transferPlayer(@PathVariable("team_id") long teamId,
                                               @PathVariable("player_id") long playerId,
                                               @PathVariable("new_team_id") long newTeamId) throws NotFoundException {
        playerService.transmitPlayerInOtherTeam(playerId, teamId, newTeamId);
        logger.info("PLayer with id: {}, that was member of the team with id: {} - was transmited into the team with id: {}",
                playerId, teamId, newTeamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

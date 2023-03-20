package test.example.leader_it.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import test.example.leader_it.dtos.PlayerDTO;
import test.example.leader_it.dtos.requests.PlayerFilterRequest;
import test.example.leader_it.exceptions.bad_request_exceptions.InvalidDataForEntityException;
import test.example.leader_it.exceptions.not_found_exceptions.PlayerNotFoundException;
import test.example.leader_it.exceptions.not_found_exceptions.TeamNotFoundException;
import test.example.leader_it.models.Player;
import test.example.leader_it.models.Team;
import test.example.leader_it.repositories.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private static final Logger logger = LogManager.getLogger(PlayerService.class);
    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.getAll().stream().map(this::convertToPlayerDTO).collect(Collectors.toList());
    }

    public List<PlayerDTO> getPlayersByTeam(long id, PlayerFilterRequest request) {
        return playerRepository.getAllByTeam(id, request).stream().map(this::convertToPlayerDTO).collect(Collectors.toList());
    }

    public void savePlayer(PlayerDTO playerDTO, long id) throws InvalidDataForEntityException {
        Player player = convertToPlayer(playerDTO);
        Optional<Team> team = teamService.getById(id);
        if (!team.isPresent()) {
            logger.warn("Team with id: {} - wasn't founded", id);
            throw new InvalidDataForEntityException("Team with id: " + id + " doesn't exist!");
        }
        player.setTeam(team.get());
        playerRepository.savePlayer(player);
    }

    public PlayerDTO getPlayerInTeam(long teamId, long playerId) {
        Optional<Player> player = playerRepository.getPlayerByTeamAndId(teamId, playerId);
        return player.map(this::convertToPlayerDTO).orElse(null);
    }

    public void deletePlayerInTeam(long teamId, long playerId) throws PlayerNotFoundException {
        Optional<Player> optionalPlayer = playerRepository.getPlayerByTeamAndId(teamId, playerId);
        checkPlayerExists(optionalPlayer, teamId, playerId);
        playerRepository.deletePlayer(optionalPlayer.get());
    }

    public void updatePlayerInTeam(PlayerDTO playerDTO, long teamId, long playerId) throws PlayerNotFoundException {
        Optional<Player> optionalPlayer = playerRepository.getPlayerByTeamAndId(teamId, playerId);
        checkPlayerExists(optionalPlayer, teamId, playerId);
        Player playerToUpdate = convertToPlayer(playerDTO);
        playerToUpdate.setTeam(optionalPlayer.get().getTeam());
        playerToUpdate.setId(playerId);
        playerRepository.updatePlayer(playerToUpdate);
    }

    public void transmitPlayerInOtherTeam(long playerId, long teamId, long newTeamId) throws PlayerNotFoundException, TeamNotFoundException {
        Optional<Player> optionalPlayer = playerRepository.getPlayerByTeamAndId(teamId, playerId);
        checkPlayerExists(optionalPlayer, teamId, playerId);
        Optional<Team> optionalTeam = teamService.getById(newTeamId);
        if (!optionalTeam.isPresent()) {
            logger.warn("Team with id: {} - not found", newTeamId);
            throw new TeamNotFoundException("Team with id: " + newTeamId + " not found");
        }
        Player player = optionalPlayer.get();
        player.setTeam(optionalTeam.get());
        playerRepository.updatePlayer(player);
    }

    private Player convertToPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }

    private void checkPlayerExists(Optional<Player> player, long teamId, long playerId) throws PlayerNotFoundException {
        if (!player.isPresent()) {
            logger.warn("Player with id: {} not found in the team with id: {}", playerId, teamId);
            throw new PlayerNotFoundException("Player with id: " + playerId + " not found in the team with id: " + teamId);
        }
    }
}

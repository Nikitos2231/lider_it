package test.example.leader_it.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import test.example.leader_it.dtos.PlayerDTO;
import test.example.leader_it.dtos.requests.PlayerFilterRequest;
import test.example.leader_it.exceptions.InvalidDataForPlayerException;
import test.example.leader_it.models.Player;
import test.example.leader_it.models.Team;
import test.example.leader_it.repositories.PlayerRepository;
import test.example.leader_it.repositories.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.getAll().stream().map(this::convertToPlayerDTO).collect(Collectors.toList());
    }

    public List<PlayerDTO> getPlayersByTeam(long id, PlayerFilterRequest request) {
        return playerRepository.getAllByTeam(id, request).stream().map(this::convertToPlayerDTO).collect(Collectors.toList());
    }

    public void savePlayer(PlayerDTO playerDTO, long id) throws InvalidDataForPlayerException {
        Player player = convertToPlayer(playerDTO);
        Optional<Team> team = teamRepository.getById(id);
        if (!team.isPresent()) {
            throw new InvalidDataForPlayerException("Team with id: " + id + " doesn't exist!");
        }
        player.setTeam(team.get());
        playerRepository.savePlayer(player);
    }

    private Player convertToPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }
}

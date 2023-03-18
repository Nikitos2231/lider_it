package test.example.leader_it.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import test.example.leader_it.dtos.PlayerDTO;
import test.example.leader_it.dtos.requests.PlayerFilterRequest;
import test.example.leader_it.models.Player;
import test.example.leader_it.repositories.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.getAll().stream().map(this::convertToPlayerDTO).collect(Collectors.toList());
    }

    public List<PlayerDTO> getPlayersByTeam(long id, PlayerFilterRequest request) {
        return playerRepository.getAllByTeam(id, request).stream().map(this::convertToPlayerDTO).collect(Collectors.toList());
    }

    private Player convertToPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }
}

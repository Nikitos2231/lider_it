package test.example.leader_it.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.example.leader_it.dtos.TeamDTO;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.models.Team;
import test.example.leader_it.repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public List<TeamDTO> getAllTeams(TeamFilterRequest request) {
        return teamRepository.getAll(request).stream().map(this::convertToTeam).collect(Collectors.toList());
    }

    public TeamDTO convertToTeam(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }
}

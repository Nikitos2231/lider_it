package test.example.leader_it.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import test.example.leader_it.dtos.TeamDTO;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.exceptions.TeamNotFoundException;
import test.example.leader_it.models.Team;
import test.example.leader_it.repositories.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public List<TeamDTO> getAllTeams(TeamFilterRequest request) {
        return teamRepository.getAll(request).stream().map(this::convertToTeam).collect(Collectors.toList());
    }

    public void saveTeam(TeamDTO teamDTO) {
        teamRepository.save(convertToTeam(teamDTO));
    }

    public void deleteTeam(long id) throws TeamNotFoundException {
        Optional<Team> team = teamRepository.getById(id);
        if (!team.isPresent()) {
            throw new TeamNotFoundException("Team with id: " + id + " doesn't exist!");
        }
        teamRepository.deleteTeam(team.get());
    }

    private Team convertToTeam(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, Team.class);
    }

    private TeamDTO convertToTeam(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }
}

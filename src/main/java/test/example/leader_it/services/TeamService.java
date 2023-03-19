package test.example.leader_it.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import test.example.leader_it.dtos.TeamDTO;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.exceptions.not_found_exceptions.TeamNotFoundException;
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
        Optional<Team> optionalTeam = getById(id);
        checkTeamExists(optionalTeam, id);
        teamRepository.deleteTeam(optionalTeam.get());
    }

    public void updateTeam(TeamDTO teamDTO, long id) throws TeamNotFoundException {
        Optional<Team> optionalTeam = getById(id);
        checkTeamExists(optionalTeam, id);
        Team team = convertToTeam(teamDTO);
        team.setId(id);
        teamRepository.updateTeam(team);
    }

    public Optional<Team> getById(long id) {
        return teamRepository.getById(id);
    }

    private Team convertToTeam(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, Team.class);
    }

    private TeamDTO convertToTeam(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }

    private void checkTeamExists(Optional<Team> team, long teamId) throws TeamNotFoundException {
        if (!team.isPresent()) {
            throw new TeamNotFoundException("Team with id: " + teamId + " doesn't exist");
        }
    }
}

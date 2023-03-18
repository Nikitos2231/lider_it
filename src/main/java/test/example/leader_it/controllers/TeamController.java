package test.example.leader_it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.example.leader_it.dtos.TeamDTO;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.services.TeamService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    public List<TeamDTO> getAllTeams(@RequestBody(required = false) TeamFilterRequest request) {
        return teamService.getAllTeams(request == null ? new TeamFilterRequest() : request);
    }
}

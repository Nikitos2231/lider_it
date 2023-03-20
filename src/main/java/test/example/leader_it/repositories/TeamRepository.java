package test.example.leader_it.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.example.leader_it.dtos.requests.TeamFilterRequest;
import test.example.leader_it.models.Team;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TeamRepository {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Team> getAll(TeamFilterRequest request) {
        Session session = sessionFactory.getCurrentSession();
        int pageSize = Integer.parseInt(request.getMaxCount());
        int currentPage = Integer.parseInt(request.getPage());

        Query<Team> query = session.createNativeQuery("SELECT * FROM team t WHERE cast(t.sport_type as varchar) like :sportType and " +
                "t.date_of_create between date(:startDate) and date(:endDate)", Team.class)
                .setParameter("sportType", request.getSportType())
                .setParameter("startDate", request.getStartDate())
                .setParameter("endDate", request.getEndDate())
                .setFirstResult((currentPage - 1) * pageSize)
                .setMaxResults(pageSize);

        return query.getResultList();
    }

    public void save(Team team) {
        Session session = sessionFactory.getCurrentSession();
        session.save(team);
    }

    public void deleteTeam(Team team) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(team);
    }

    @Transactional(readOnly = true)
    public Optional<Team> getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Team team = session.get(Team.class, id);
        return team == null ? Optional.empty() : Optional.of(team);
    }

    public void updateTeam(Team team) {
        System.out.println(team.toString());
        Session session = sessionFactory.getCurrentSession();
        session.update(team);
    }

}

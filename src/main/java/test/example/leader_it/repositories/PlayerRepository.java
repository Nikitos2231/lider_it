package test.example.leader_it.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.example.leader_it.dtos.requests.PlayerFilterRequest;
import test.example.leader_it.models.Player;
import test.example.leader_it.models.Team;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PlayerRepository {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Player> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Player p", Player.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Player> getAllByTeam(long id, PlayerFilterRequest request) {
        Session session = sessionFactory.getCurrentSession();

        int pageSize = Integer.parseInt(request.getMaxCount());
        int currentPage = Integer.parseInt(request.getPage());

        return session.createQuery("SELECT p FROM Player p where p.team.id = :id and p.roleOrPosition like :roleOrPosition", Player.class)
                .setParameter("id", id)
                .setParameter("roleOrPosition", request.getRoleOrPosition())
                .setFirstResult((currentPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public void savePlayer(Player player) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(player);
    }

    @Transactional(readOnly = true)
    public Optional<Player> getPlayerByTeamAndId(long teamId, long playerId) {
        Session session = sessionFactory.getCurrentSession();
        List<Player> players = session.createQuery("SELECT p FROM Player p where p.team.id = :teamId and p.id = :playerId", Player.class)
                .setParameter("teamId", teamId)
                .setParameter("playerId", playerId).getResultList();
        return players.size() == 1 ? Optional.of(players.get(0)) : Optional.empty();
    }

    public void deletePlayer(Player player) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(player);
    }
}

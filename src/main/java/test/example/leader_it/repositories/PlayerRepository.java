package test.example.leader_it.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.example.leader_it.dtos.requests.PlayerFilterRequest;
import test.example.leader_it.models.Player;

import java.util.List;

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
}

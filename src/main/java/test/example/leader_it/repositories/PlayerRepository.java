package test.example.leader_it.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
}

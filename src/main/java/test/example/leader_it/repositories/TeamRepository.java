package test.example.leader_it.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.example.leader_it.models.Team;

import java.sql.ResultSet;
import java.util.List;

@Repository
@Transactional
public class TeamRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TeamRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Team> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT t FROM Team t", Team.class).getResultList();
    }

}

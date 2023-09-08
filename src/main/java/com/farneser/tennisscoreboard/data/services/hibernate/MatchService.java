package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.utils.HibernateFactory;

import java.util.List;

public class MatchService extends EntityService<Match> {
    @Override
    public List<Match> get() {
        var session = HibernateFactory.getSessionFactory().openSession();

        return session.createQuery("FROM Match", Match.class).getResultList();
    }
}

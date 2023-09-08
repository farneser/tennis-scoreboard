package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.utils.HibernateFactory;

import java.util.List;

public class MatchService extends EntityService<Match> {
    @Override
    public List<Match> get() {
        var session = HibernateFactory.getSessionFactory().openSession();

        return session.createQuery("FROM Match", Match.class).getResultList();
    }

    // demo data
    static {
        var playerService = new PlayerService();
        var matchService = new MatchService();

        var player1 = new Player("Andrey");
        var player2 = new Player("Vlad");
        var player3 = new Player("Sasha");
        var player4 = new Player("Jack");
        var player5 = new Player("Yurik");
        var player6 = new Player("Maksim");

        playerService.persist(player1);
        playerService.persist(player2);
        playerService.persist(player3);
        playerService.persist(player4);
        playerService.persist(player5);
        playerService.persist(player6);

        matchService.persist(new Match(player1, player2, player1));
        matchService.persist(new Match(player2, player1, player1));
        matchService.persist(new Match(player3, player2, player2));
        matchService.persist(new Match(player4, player6, player6));
        matchService.persist(new Match(player1, player5, player5));
        matchService.persist(new Match(player4, player2, player2));
        matchService.persist(new Match(player1, player5, player1));
    }
}

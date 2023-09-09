package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.utils.HibernateFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        var players = new ArrayList<Player>();

        players.add(new Player("Andrey"));
        players.add(new Player("Vlad"));
        players.add(new Player("Sasha"));
        players.add(new Player("Jack"));
        players.add(new Player("Yurik"));
        players.add(new Player("Maksim"));

        players.forEach(playerService::persist);

        var random = new Random();

        for (var i = 0; i < 50; i++) {

            var index1 = random.nextInt(players.size());
            Player player1 = players.get(index1);

            int index2;
            Player player2;

            do {
                index2 = random.nextInt(players.size());
                player2 = players.get(index2);
            } while (index2 == index1);

            matchService.persist(new Match(player1, player2, random.nextBoolean() ? player1 : player2));
        }

    }
}

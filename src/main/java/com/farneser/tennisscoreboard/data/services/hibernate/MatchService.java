package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.utils.HibernateFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class MatchService extends EntityService<Match> {

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

        for (var i = 0; i < 150; i++) {

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

    private final Logger logger = Logger.getLogger(MatchService.class.getName());

    public static void main(String[] args) {
        var matchService = new MatchService();

        var lastPage = matchService.getLastPage(15);
        System.out.println(lastPage);
        var count = matchService.getCount();
        System.out.println(count);
        var res = matchService.get(3, 12, null);

        System.out.println(res);

        res = matchService.get(0, 10, "Vlad");

        System.out.println(res);
    }

    public Long getCount() {
        var session = HibernateFactory.getSessionFactory().openSession();

        var message = "creating query \"Select count(m.id) FROM Match m\"";

        logger.info("started " + message);

        var countQuery = session.createSelectionQuery("Select count(m.id) FROM Match m");

        logger.info("successfully created " + message);

        var res = countQuery.uniqueResult();

        logger.info("successfully finished " + message + " with result = " + res);

        return (Long) res;
    }

    public int getLastPage(int pageSize) {
        var matchesCount = getCount();
        return getLastPage(pageSize, matchesCount);
    }

    public int getLastPage(int pageSize, Long matchesCount) {
        return (int) (Math.ceil((double) matchesCount / pageSize));
    }

    public List<Match> get(int page, int pageSize, String filterName) {
        var session = HibernateFactory.getSessionFactory().openSession();

        var message = "creating query of selection finished matches with pagination page=" + page;

        logger.info("started " + message);

        var query = session.createQuery(
                """
                        SELECT m
                        FROM Match m
                        LEFT JOIN FETCH m.player1 p1
                        LEFT JOIN FETCH m.player2 p2
                        LEFT JOIN FETCH m.winner w
                        WHERE (:playerName IS NULL) OR
                            (LOWER(p1.name) LIKE LOWER(:playerName)) OR
                            (LOWER(p2.name) LIKE LOWER(:playerName)) OR
                            (LOWER(w.name) LIKE LOWER(:playerName))
                        """
                , Match.class);

        logger.info("successfully created " + message);

        query.setParameter("playerName", filterName);

        query.setFirstResult((page - 1) * pageSize);

        query.setMaxResults(pageSize);

        logger.info("successfully finished " + message);

        return query.getResultList();
    }

    @Override
    public List<Match> get() {
        var session = HibernateFactory.getSessionFactory().openSession();

        return session.createQuery("FROM Match", Match.class).getResultList();
    }
}

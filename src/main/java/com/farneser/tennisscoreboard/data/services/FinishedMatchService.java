package com.farneser.tennisscoreboard.data.services;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.hibernate.EntityService;
import com.farneser.tennisscoreboard.data.services.hibernate.MatchService;
import com.farneser.tennisscoreboard.data.services.hibernate.PlayerService;

import java.util.logging.Logger;

public class FinishedMatchService {
    private final Logger logger = Logger.getLogger(FinishedMatchService.class.getName());

    private final PlayerService playerService = new PlayerService();
    private final EntityService<Match> matchService = new MatchService();

    public void save(CurrentMatch match) {
        savePlayers(match);

        var finishedMatch = saveMatch(match);
        logger.info("created match " + finishedMatch + " created");
    }

    private void savePlayers(CurrentMatch match) {
        playerService.persist(match.getFirstPlayer());
        playerService.persist(match.getSecondPlayer());
    }

    private Match saveMatch(CurrentMatch currentMatch) {
        var match = currentMatch.getFinishedMatch();

        matchService.persist(match);

        return match;
    }
}

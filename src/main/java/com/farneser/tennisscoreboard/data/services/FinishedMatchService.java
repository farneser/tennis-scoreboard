package com.farneser.tennisscoreboard.data.services;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.hibernate.EntityService;
import com.farneser.tennisscoreboard.data.services.hibernate.MatchService;
import com.farneser.tennisscoreboard.data.services.hibernate.PlayerService;

public class FinishedMatchService {
    private final PlayerService playerService = new PlayerService();
    private final EntityService<Match> matchService = new MatchService();

    public void save(CurrentMatch match) {

        savePlayers(match);
        System.out.println("created player1 " + match.getFirstPlayer() + " and player2 " + match.getSecondPlayer() + " created");

        var finishedMatch = saveMatch(match);
        System.out.println("created match " + finishedMatch + " created");
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

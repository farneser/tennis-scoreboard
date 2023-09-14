package com.farneser.tennisscoreboard.data.services.currentmatches;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.services.score.PlayersScore;
import com.farneser.tennisscoreboard.data.services.score.calculator.GameCalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.CurrentGameCalculator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

@Data
public class CurrentMatch {

    private final UUID id;

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final int setsCount;
    private final Logger logger = Logger.getLogger(CurrentMatch.class.getName());
    public boolean isCurrentFirst;
    private Player winnerPlayer;
    private PlayersScore<Integer> currentSet = new PlayersScore<>();
    private List<PlayersScore<Integer>> setScores = new ArrayList<>();
    private CurrentGameCalculator<?> currentGame;

    public CurrentMatch(UUID id, Player firstPlayer, Player secondPlayer, int setsCount) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.setsCount = setsCount;

        var rnd = new Random();

        isCurrentFirst = rnd.nextBoolean();

        this.currentSet.setFirstPlayerScore(0);
        this.currentSet.setSecondPlayerScore(0);

        currentGame = new GameCalculator();
    }

    /**
     * @return the count of sets that player needs to win all game
     */
    public int winMatchesCount() {
        return (setsCount + 1) / 2;
    }

    public void refreshCurrentSet() {
        logger.info("start append " + currentSet + " to set scores");
        setScores.add(currentSet);

        logger.info("append " + currentSet + " successfully finished");

        currentSet = new PlayersScore<>();

        currentSet.setFirstPlayerScore(0);
        currentSet.setSecondPlayerScore(0);
    }

    public Match getFinishedMatch() {
        return new Match(firstPlayer, secondPlayer, winnerPlayer);
    }
}

package com.farneser.tennisscoreboard.data.services.currentmatches;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.services.score.GamePoints;
import com.farneser.tennisscoreboard.data.services.score.PlayersScore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CurrentMatch {

    private final UUID id;

    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player winnerPlayer;
    private PlayersScore<Integer> currentSet = new PlayersScore<>();
    private final int setsCount;
    private List<PlayersScore<Integer>> setScores = new ArrayList<>();
    private PlayersScore<GamePoints> gameScore = new PlayersScore<>();

    public CurrentMatch(UUID id, Player firstPlayer, Player secondPlayer, int setsCount) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.setsCount = setsCount;

        this.gameScore.setFirstPlayerScore(GamePoints.Zero);
        this.gameScore.setSecondPlayerScore(GamePoints.Zero);

        this.currentSet.setFirstPlayerScore(0);
        this.currentSet.setSecondPlayerScore(0);
    }

    /**
     * @return the count of sets that player needs to win all game
     */
    public int winMatchesCount() {
        return (setsCount + 1) / 2;
    }

    public void refreshCurrentSet() {
        setScores.add(currentSet);

        currentSet = new PlayersScore<>();

        currentSet.setFirstPlayerScore(0);
        currentSet.setSecondPlayerScore(0);
    }
}

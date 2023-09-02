package com.farneser.tennisscoreboard.data.services.currentmatches;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.services.score.GamePoints;
import com.farneser.tennisscoreboard.data.services.score.PlayersScore;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Data
public class CurrentMatch {

    private final UUID id;

    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player winnerPlayer;

    private final int setsCount;
    private List<PlayersScore<Integer>> setScores;
    private PlayersScore<GamePoints> gameScore;

    public CurrentMatch(UUID id, Player firstPlayer, Player secondPlayer, int setsCount) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameScore = new PlayersScore<>();
        this.setsCount = setsCount;

        this.gameScore.setFirstPlayer(GamePoints.Zero);
        this.gameScore.setSecondPlayer(GamePoints.Zero);
    }
}

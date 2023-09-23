package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.GamePoints;
import com.farneser.tennisscoreboard.data.services.score.State;

import java.util.logging.Logger;

public class GameCalculator extends CurrentGameCalculator<GamePoints> {
    private final Logger logger = Logger.getLogger(GameCalculator.class.getName());

    @Override
    public State process(CurrentMatch match, WinnerType winner) {

        if (this.getWinner(winner).ordinal() > this.getLooser(winner).ordinal()) {
            if (this.getWinner(winner).ordinal() >= GamePoints.FORTY.ordinal()) {

                this.setFirstPlayerScore(GamePoints.LOVE);
                this.setSecondPlayerScore(GamePoints.LOVE);

                if (winner == WinnerType.FirstPlayer) {
                    return State.FIRST_PLAYER;
                }

                return State.SECOND_PLAYER;
            }

        }

        logger.info("game " + match.getId() + " continues with score " + this);

        if (this.getWinner(winner) != GamePoints.ADVANTAGE) {
            this.setWinner(this.getWinner(winner).next(), winner);
        }

        if (this.getWinner(winner) == this.getLooser(winner)) {
            // 40:40, AD:AD, 40:AD, AD:40
            if (this.getWinner(winner) == GamePoints.ADVANTAGE) {
                this.setFirstPlayerScore(GamePoints.FORTY);
                this.setSecondPlayerScore(GamePoints.FORTY);
            }
        }

        return State.GAME_IN_PROCESS;
    }

    @Override
    protected GamePoints getInitialValue() {
        return GamePoints.LOVE;
    }
}

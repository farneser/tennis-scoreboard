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
            if (this.getWinner(winner).ordinal() >= GamePoints.Forty.ordinal()) {

                this.setFirstPlayerScore(GamePoints.Zero);
                this.setSecondPlayerScore(GamePoints.Zero);

                if (winner == WinnerType.FirstPlayer) {
                    return State.FirstPlayer;
                }

                return State.SecondPlayer;
            }

        }

        logger.info("game " + match.getId() + " continues with score " + this);

        if (this.getWinner(winner) != GamePoints.Advantage) {
            this.setWinner(this.getWinner(winner).next(), winner);
        }

        if (this.getWinner(winner) == this.getLooser(winner)) {
            // 40:40, AD:AD, 40:AD, AD:40
            if (this.getWinner(winner) == GamePoints.Advantage) {
                this.setFirstPlayerScore(GamePoints.Forty);
                this.setSecondPlayerScore(GamePoints.Forty);
            }
        }

        return State.GameInProcess;
    }

    @Override
    protected GamePoints getInitialValue() {
        return GamePoints.Zero;
    }
}

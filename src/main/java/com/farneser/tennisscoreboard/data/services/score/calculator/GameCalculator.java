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

        if (gameScore.getWinner(winner).ordinal() > gameScore.getLooser(winner).ordinal()) {
            if (gameScore.getWinner(winner).ordinal() >= GamePoints.Forty.ordinal()) {

                gameScore.setFirstPlayerScore(GamePoints.Zero);
                gameScore.setSecondPlayerScore(GamePoints.Zero);

                if (winner == WinnerType.FirstPlayer) {
                    return State.FirstPlayer;
                }

                return State.SecondPlayer;
            }

        }

        logger.info("game " + match.getId() + " continues with score " + gameScore);

        if (gameScore.getWinner(winner) != GamePoints.Advantage) {
            gameScore.setWinner(gameScore.getWinner(winner).next(), winner);
        }

        if (gameScore.getWinner(winner) == gameScore.getLooser(winner)) {
            // 40:40, AD:AD, 40:AD, AD:40
            if (gameScore.getWinner(winner) == GamePoints.Advantage) {
                gameScore.setFirstPlayerScore(GamePoints.Forty);
                gameScore.setSecondPlayerScore(GamePoints.Forty);
            }
        }

        return State.GameInProcess;
    }

    @Override
    protected GamePoints getInitialValue() {
        return GamePoints.Zero;
    }
}

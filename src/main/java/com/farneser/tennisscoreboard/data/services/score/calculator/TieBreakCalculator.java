package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;

import java.util.logging.Logger;

public class TieBreakCalculator extends CurrentGameCalculator<Integer> {
    private final Logger logger = Logger.getLogger(TieBreakCalculator.class.getName());

    @Override
    public State process(CurrentMatch match, WinnerType winner) {

        gameScore.setWinner(gameScore.getWinner(winner) + 1, winner);

        logger.info("tie break " + match.getId() + " " + gameScore);

        if (gameScore.getWinner(winner) >= 6 && gameScore.getWinner(winner) - gameScore.getLooser(winner) >= 2) {
            return gameScore.getWinner(winner).equals(gameScore.getFirstPlayerScore()) ? State.FirstPlayer : State.SecondPlayer;
        }

        return State.GameInProcess;
    }

    @Override
    protected Integer getInitialValue() {
        return 0;
    }
}

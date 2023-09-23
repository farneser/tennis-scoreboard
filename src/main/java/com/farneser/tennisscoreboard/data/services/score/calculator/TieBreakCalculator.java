package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;

import java.util.logging.Logger;

public class TieBreakCalculator extends CurrentGameCalculator<Integer> {
    private final Logger logger = Logger.getLogger(TieBreakCalculator.class.getName());

    @Override
    public State process(CurrentMatch match, WinnerType winner) {

        this.setWinner(this.getWinner(winner) + 1, winner);

        logger.info("tie break " + match.getId() + " " + this);

        if (this.getWinner(winner) >= 6 && this.getWinner(winner) - this.getLooser(winner) >= 2) {
            return this.getWinner(winner).equals(this.getFirstPlayerScore()) ? State.FIRST_PLAYER : State.SECOND_PLAYER;
        }

        return State.GAME_IN_PROCESS;
    }

    @Override
    protected Integer getInitialValue() {
        return 0;
    }
}

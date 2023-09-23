package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;

public class SetCalculator implements ICalculator {
    @Override
    public State process(CurrentMatch match, WinnerType winner) {

        if (match.getCurrentGame().process(match, winner) == State.GAME_IN_PROCESS) {
            return State.GAME_IN_PROCESS;
        }

        match.getCurrentSet().setWinner(match.getCurrentSet().getWinner(winner) + 1, winner);

        if (match.getCurrentSet().getWinner(winner) > 5) {

            if (match.getCurrentSet().getWinner(winner) == 7 || match.getCurrentSet().getWinner(winner) == 6 && match.getCurrentSet().getLooser(winner) <= 4) {
                match.refreshCurrentSet();

                match.setCurrentGame(new GameCalculator());

                return WinnerType.convertToState(winner);
            } else if (match.getCurrentSet().getFirstPlayerScore().equals(match.getCurrentSet().getSecondPlayerScore()) && match.getCurrentSet().getSecondPlayerScore() == 6) {
                match.setCurrentGame(new TieBreakCalculator());
            }
        }

        return State.GAME_IN_PROCESS;
    }
}

package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;

public class SetCalculator implements ICalculator {
    @Override
    public State process(CurrentMatch match, WinnerType winner) {
        match.getCurrentSet().setWinner(match.getCurrentSet().getWinner(winner) + 1, winner);

        if (match.getCurrentSet().getWinner(winner) > 5) {
            if (match.getCurrentSet().getWinner(winner) - match.getCurrentSet().getLooser(winner) >= 2 || match.getCurrentSet().getWinner(winner) == 7) {
                match.refreshCurrentSet();
                return WinnerType.convertToState(winner);
            }
        }

        return State.GameInProcess;
    }
}

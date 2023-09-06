package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;

public class MatchCalculator implements ICalculator {
    @Override
    public State process(CurrentMatch match, WinnerType winner) {

        System.out.println(winner + " wins the set");

        var setsWonCounter = 0;

        for (var set : match.getSetScores()) {

            if (set.getWinner(winner) > set.getLooser(winner)) {
                setsWonCounter++;
            }

        }

        if (match.getSetScores().size() >= match.winMatchesCount() &&
                setsWonCounter >= match.winMatchesCount()) {

            match.setWinnerPlayer(winner == WinnerType.FirstPlayer ?
                    match.getFirstPlayer() :
                    match.getSecondPlayer()
            );

            return WinnerType.convertToState(winner);
        }

        return State.GameInProcess;
    }
}

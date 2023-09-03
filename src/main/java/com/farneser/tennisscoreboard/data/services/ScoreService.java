package com.farneser.tennisscoreboard.data.services;

import com.farneser.tennisscoreboard.data.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;
import com.farneser.tennisscoreboard.data.services.score.calculator.GameCalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.ICalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.MatchCalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.SetCalculator;

public class ScoreService implements ICalculator {

    public State process(CurrentMatch match, WinnerType winner) {

        var gameCalculator = new GameCalculator();
        var setCalculator = new SetCalculator();
        var matchCalculator = new MatchCalculator();

        var gameResult = gameCalculator.process(match, winner);
        System.out.println("game " + match.getId() + " " + gameResult);

        if (gameResult != State.GameInProcess) {

            var setResult = setCalculator.process(match, winner);
            System.out.println("set " + match.getId() + " " + setResult);

            if (setResult != State.GameInProcess) {
                var matchResult = matchCalculator.process(match, winner);
                System.out.println("match " + match.getId() + " " + matchResult);

                return matchResult;
            }
        }

        return State.GameInProcess;
    }

}

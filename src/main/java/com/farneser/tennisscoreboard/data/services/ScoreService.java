package com.farneser.tennisscoreboard.data.services;

import com.farneser.tennisscoreboard.data.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;
import com.farneser.tennisscoreboard.data.services.score.calculator.GameCalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.SetCalculator;

public class ScoreService {

    public void process(CurrentMatch match, WinnerType winner) {

        var gameCalculator = new GameCalculator();
        var setCalculator = new SetCalculator();

        var gameResult = gameCalculator.process(match, winner);
        System.out.println("game " + match.getId() + " " + gameResult);

        if (gameResult != State.GameInProcess) {

            var setResult = setCalculator.process(match, winner);
            System.out.println("set " + match.getId() + " " + setResult);
        }


    }

}

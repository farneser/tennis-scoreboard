package com.farneser.tennisscoreboard.data.services;

import com.farneser.tennisscoreboard.data.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.calculator.GameCalculator;

public class ScoreService {

    public void process(CurrentMatch match, WinnerType winner) {

        var gameCalculator = new GameCalculator();
        var result = gameCalculator.process(match, winner);

        System.out.println(result);
    }

}

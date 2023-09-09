package com.farneser.tennisscoreboard.data.services;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;
import com.farneser.tennisscoreboard.data.services.score.calculator.GameCalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.ICalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.MatchCalculator;
import com.farneser.tennisscoreboard.data.services.score.calculator.SetCalculator;

import java.util.logging.Logger;

public class ScoreService implements ICalculator {
    private final Logger logger = Logger.getLogger(ScoreService.class.getName());

    public State process(CurrentMatch match, WinnerType winner) {

        var gameCalculator = new GameCalculator();
        var setCalculator = new SetCalculator();
        var matchCalculator = new MatchCalculator();

        match.isCurrentFirst = !match.isCurrentFirst;

        var gameResult = gameCalculator.process(match, winner);
        logger.info("game " + match.getId() + " " + gameResult);

        if (gameResult != State.GameInProcess) {

            var setResult = setCalculator.process(match, winner);
            logger.info("set " + match.getId() + " " + setResult);

            if (setResult != State.GameInProcess) {
                var matchResult = matchCalculator.process(match, winner);
                logger.info("match " + match.getId() + " " + matchResult);

                return matchResult;
            }
        }

        return State.GameInProcess;
    }
}

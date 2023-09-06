package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;

public interface ICalculator {
    /**
     * calculator processor
     *
     * @param match  defines match that winner win
     * @param winner defines winner who win the match
     * @return the state of current game
     */
    State process(CurrentMatch match, WinnerType winner);
}

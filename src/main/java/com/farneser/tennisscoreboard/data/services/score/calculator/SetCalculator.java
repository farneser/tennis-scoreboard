package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;

public class SetCalculator implements ICalculator {
    @Override
    public State process(CurrentMatch match, WinnerType winner) {
        System.out.println(winner + " in set service");


        return null;
    }
}

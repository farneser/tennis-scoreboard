package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.services.score.PlayersScore;
import lombok.Getter;


@Getter
public abstract class CurrentGameCalculator<T> extends PlayersScore<T> implements ICalculator {
    public CurrentGameCalculator() {
        setFirstPlayerScore(getInitialValue());
        setSecondPlayerScore(getInitialValue());
    }

    protected abstract T getInitialValue();
}

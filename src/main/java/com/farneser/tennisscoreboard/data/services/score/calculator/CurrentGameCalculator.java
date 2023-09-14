package com.farneser.tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.services.score.PlayersScore;
import lombok.Getter;
import lombok.Setter;


@Getter
public abstract class CurrentGameCalculator<T> extends PlayersScore<T> implements ICalculator {
    @Setter
    protected PlayersScore<T> gameScore = new PlayersScore<>();

    public CurrentGameCalculator() {
        gameScore.setFirstPlayerScore(getInitialValue());
        gameScore.setSecondPlayerScore(getInitialValue());
    }

    protected abstract T getInitialValue();
}

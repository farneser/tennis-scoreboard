package com.farneser.tennisscoreboard.data.entities.dto;

import com.farneser.tennisscoreboard.data.services.score.State;

public enum WinnerType {
    FirstPlayer,
    SecondPlayer;

    public static State convertToState(WinnerType winner) {
        if (winner == WinnerType.FirstPlayer) {
            return State.FIRST_PLAYER;
        }

        return State.SECOND_PLAYER;
    }
}

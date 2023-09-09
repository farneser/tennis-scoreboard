package com.farneser.tennisscoreboard.data.services.score;

import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import lombok.Data;

@Data
public class PlayersScore<T> {

    private T firstPlayerScore;
    private T secondPlayerScore;

    public void setWinner(T playerScore, WinnerType winner) {

        if (winner == WinnerType.FirstPlayer) {
            firstPlayerScore = playerScore;
        } else {
            secondPlayerScore = playerScore;
        }
    }

    public T getWinner(WinnerType winner) {
        if (winner == WinnerType.FirstPlayer) {
            return firstPlayerScore;
        } else {
            return secondPlayerScore;
        }
    }

    public T getLooser(WinnerType winner) {
        return getWinner(winner == WinnerType.FirstPlayer ? WinnerType.SecondPlayer : WinnerType.FirstPlayer);
    }
}

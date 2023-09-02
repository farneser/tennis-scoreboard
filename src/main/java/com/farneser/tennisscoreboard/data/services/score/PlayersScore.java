package com.farneser.tennisscoreboard.data.services.score;

import com.farneser.tennisscoreboard.data.dto.WinnerType;
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
}

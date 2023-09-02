package com.farneser.tennisscoreboard.data.dto;

import lombok.Getter;

@Getter
public class CreateMatchDto {
    private final String firstPlayerName;
    private final String secondPlayerName;
    private final int setsCount;

    public CreateMatchDto(String firstPlayerName, String secondPlayerName, int setsCount) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.setsCount = setsCount;
    }
}

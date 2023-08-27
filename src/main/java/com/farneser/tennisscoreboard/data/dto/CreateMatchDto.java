package com.farneser.tennisscoreboard.data.dto;

import lombok.Getter;

@Getter
public class CreateMatchDto {
    private final String firstPlayerName;
    private final String secondPlayerName;

    public CreateMatchDto(String firstPlayerName, String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }
}

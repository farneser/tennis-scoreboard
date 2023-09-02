package com.farneser.tennisscoreboard.data.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GameScoreDto {
    private final UUID id;
    private final WinnerType winner;

    public GameScoreDto(UUID id, WinnerType winner) {
        this.id = id;
        this.winner = winner;
    }
}
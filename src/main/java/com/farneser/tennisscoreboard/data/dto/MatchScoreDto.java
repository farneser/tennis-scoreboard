package com.farneser.tennisscoreboard.data.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchScoreDto {
    private final UUID id;
    private final WinnerType winner;

    public MatchScoreDto(UUID id, WinnerType winner) {
        this.id = id;
        this.winner = winner;
    }
}
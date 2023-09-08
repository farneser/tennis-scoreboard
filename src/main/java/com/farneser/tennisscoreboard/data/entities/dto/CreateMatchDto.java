package com.farneser.tennisscoreboard.data.entities.dto;

public record CreateMatchDto(String firstPlayerName, String secondPlayerName, int setsCount) {
}

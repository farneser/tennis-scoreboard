package com.farneser.tennisscoreboard.data.entities.dto;

import java.util.UUID;

public record GameScoreDto(UUID id, WinnerType winner) {
}
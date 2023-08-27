package com.farneser.tennisscoreboard.data.services.currentmatches;

import com.farneser.tennisscoreboard.data.entities.Player;
import lombok.Data;

import java.util.UUID;

@Data
public class CurrentMatch {

    private final UUID id;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player winnerPlayer;

    public CurrentMatch(UUID id, Player firstPlayer, Player secondPlayer) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
}

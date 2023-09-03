package com.farneser.tennisscoreboard.data.services.currentmatches;

import com.farneser.tennisscoreboard.data.entities.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CurrentMatchesService {
    private static CurrentMatchesService instance;
    private final Map<UUID, CurrentMatch> matches = new HashMap<>();

    private CurrentMatchesService() {
    }

    public static CurrentMatchesService getInstance() {

        if (instance == null) {
            instance = new CurrentMatchesService();
        }

        return instance;
    }

    public UUID create(Player firstPlayer, Player secondPlayer, int setsCount) {
        var id = UUID.randomUUID();

        var currentMatch = new CurrentMatch(id, firstPlayer, secondPlayer, setsCount);

        matches.put(id, currentMatch);

        return id;
    }

    public CurrentMatch get(UUID id) {
        return matches.get(id);
    }
}

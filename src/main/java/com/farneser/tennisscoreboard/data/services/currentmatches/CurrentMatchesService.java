package com.farneser.tennisscoreboard.data.services.currentmatches;

import com.farneser.tennisscoreboard.data.entities.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class CurrentMatchesService {
    private static final Logger logger = Logger.getLogger(CurrentMatchesService.class.getName());
    private static CurrentMatchesService instance;
    private final Map<UUID, CurrentMatch> matches = new ConcurrentHashMap<>();

    private CurrentMatchesService() {
    }

    public static CurrentMatchesService getInstance() {

        if (instance == null) {
            logger.info("current matches service initializing started");

            instance = new CurrentMatchesService();

            logger.info("current matches service initializing successfully initialized");
        }

        logger.info("current matches service instance found");

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

package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Match;

import java.util.List;

public class MatchService extends EntityService<Match> {
    @Override
    public List<Match> get() {
        return get(Match.class);
    }
}

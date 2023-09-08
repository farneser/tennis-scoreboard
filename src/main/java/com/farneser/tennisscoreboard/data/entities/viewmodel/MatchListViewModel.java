package com.farneser.tennisscoreboard.data.entities.viewmodel;

import com.farneser.tennisscoreboard.data.entities.Match;

import java.util.List;

public record MatchListViewModel(List<Match> matches, int currentPageNumber, int lastPageNumber) {
    @Override
    public String toString() {
        return "MatchListViewModel(matches=" + matches.toString() + ", currentPageNumber=" + currentPageNumber + ", lastPageNumber=" + lastPageNumber + ")";
    }
}

package com.farneser.tennisscoreboard.data.services;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.dto.MatchesDto;
import com.farneser.tennisscoreboard.data.entities.viewmodel.MatchListViewModel;
import com.farneser.tennisscoreboard.data.services.hibernate.MatchService;

import java.util.ArrayList;
import java.util.List;

public class MatchesService {

    private final MatchService matchService = new MatchService();

    public MatchListViewModel persist(MatchesDto matchesDto) {
        var pageSize = 10;

        List<Match> matches = new ArrayList<>();

        if (matchesDto.pageNumber() <= matchService.getLastPage(pageSize) && matchesDto.pageNumber() > 0) {
            matches = matchService.get(
                    matchesDto.pageNumber(),
                    pageSize,
                    matchesDto.searchText());
        }

        return new MatchListViewModel(
                matches,
                matchesDto.pageNumber(),
                matchService.getLastPage(pageSize)
        );
    }

}

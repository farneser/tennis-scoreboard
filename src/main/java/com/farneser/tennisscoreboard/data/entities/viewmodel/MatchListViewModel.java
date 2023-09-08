package com.farneser.tennisscoreboard.data.entities.viewmodel;

import com.farneser.tennisscoreboard.data.entities.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public record MatchListViewModel(List<Match> matches, int currentPageNumber, int lastPageNumber) {
}


package com.farneser.tennisscoreboard.data.utils;

import com.farneser.tennisscoreboard.data.entities.dto.CreateMatchDto;
import com.farneser.tennisscoreboard.data.entities.dto.GameScoreDto;
import com.farneser.tennisscoreboard.data.entities.dto.MatchesDto;
import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class ParseParamsUtil {

    public static CreateMatchDto ParsePostNewMatch(HttpServletRequest req) {

        // there we can throw exception if player1 or player 2 is null
        // handle exception and return error message

        var player1 = req.getParameter("player1");
        var player2 = req.getParameter("player2");
        var setsCount = Integer.parseInt(req.getParameter("setsCount"));

        if (setsCount != 3 && setsCount != 5) {
            setsCount = 3;
        }

        return new CreateMatchDto(player1, player2, setsCount);
    }

    public static UUID ParseGetMatchScore(HttpServletRequest req) {

        var id = req.getParameter("uuid");

        return UUID.fromString(id);
    }

    public static GameScoreDto ParsePostMatchScope(HttpServletRequest req) {
        var id = ParseParamsUtil.ParseGetMatchScore(req);

        var winner = WinnerType.FirstPlayer;

        if (req.getParameter("winner").equals("second")) {
            winner = WinnerType.SecondPlayer;
        }

        return new GameScoreDto(id, winner);
    }

    public static MatchesDto ParseGetMatches(HttpServletRequest req) {
        var page = 1;

        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignored) {
        }

        var filter = req.getParameter("filter_by_player_name");

        if (filter != null && filter.isEmpty()) {
            filter = null;
        }

        return new MatchesDto(page, filter);
    }
}

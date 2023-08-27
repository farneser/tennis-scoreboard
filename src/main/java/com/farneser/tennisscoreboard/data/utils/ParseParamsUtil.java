package com.farneser.tennisscoreboard.data.utils;

import com.farneser.tennisscoreboard.data.dto.CreateMatchDto;
import com.farneser.tennisscoreboard.data.dto.MatchScoreDto;
import com.farneser.tennisscoreboard.data.dto.WinnerType;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class ParseParamsUtil {

    public static CreateMatchDto ParsePostNewMatch(HttpServletRequest req) {

        // there we can throw exception if player1 or player 2 is null
        // handle exception and return error message

        var player1 = req.getParameter("player1");
        var player2 = req.getParameter("player2");

        return new CreateMatchDto(player1, player2);
    }

    public static UUID ParseGetMatchScore(HttpServletRequest req) {

        var id = req.getParameter("uuid");

        return UUID.fromString(id);
    }

    public static MatchScoreDto ParsePostMatchScope(HttpServletRequest req) {
        var id = ParseParamsUtil.ParseGetMatchScore(req);

        var winner = WinnerType.FirstPlayer;

        if (req.getParameter("winner").equals("second")) {
            winner = WinnerType.SecondPlayer;
        }

        return new MatchScoreDto(id, winner);
    }

}

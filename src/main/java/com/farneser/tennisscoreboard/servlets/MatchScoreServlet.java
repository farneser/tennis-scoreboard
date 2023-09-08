package com.farneser.tennisscoreboard.servlets;

import com.farneser.tennisscoreboard.data.services.FinishedMatchService;
import com.farneser.tennisscoreboard.data.services.ScoreService;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatchesService;
import com.farneser.tennisscoreboard.data.services.score.State;
import com.farneser.tennisscoreboard.data.utils.ParseParamsUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "match-score", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final CurrentMatchesService currentMatchesService = CurrentMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var servletContext = getServletContext();

        servletContext.setAttribute(
                "currentMatch",
                currentMatchesService.get(ParseParamsUtil.ParseGetMatchScore(req))
        );

        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var gameScore = ParseParamsUtil.ParsePostMatchScope(req);

        var currentMatch = currentMatchesService.get(gameScore.id());

        if (currentMatch.getWinnerPlayer() == null) {
            var state = new ScoreService().process(currentMatch, gameScore.winner());

            if (state != State.GameInProcess) {
                new FinishedMatchService().save(currentMatch);
            }

        }

        resp.sendRedirect("match-score?uuid=" + gameScore.id());
    }
}
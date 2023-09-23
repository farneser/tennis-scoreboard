package com.farneser.tennisscoreboard.servlets;

import com.farneser.tennisscoreboard.data.exceptons.InternalServerException;
import com.farneser.tennisscoreboard.data.services.FinishedMatchService;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatchesService;
import com.farneser.tennisscoreboard.data.services.score.State;
import com.farneser.tennisscoreboard.data.services.score.calculator.MatchCalculator;
import com.farneser.tennisscoreboard.data.utils.ParseParamsUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "match-score", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final CurrentMatchesService currentMatchesService = CurrentMatchesService.getInstance();
    private final Logger logger = Logger.getLogger(MatchScoreServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var servletContext = getServletContext();

        servletContext.setAttribute(
                "currentMatch",
                currentMatchesService.get(ParseParamsUtil.parseGetMatchScore(req))
        );

        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        var servletContext = getServletContext();

        var gameScore = ParseParamsUtil.parsePostMatchScope(req);

        var currentMatch = currentMatchesService.get(gameScore.id());

        if (currentMatch.getWinnerPlayer() == null) {

            logger.info("match " + currentMatch.getId() + " in progress");

            var state = new MatchCalculator().process(currentMatch, gameScore.winner());

            if (state != State.GAME_IN_PROCESS) {
                logger.info("match " + currentMatch.getId() + " finished with winner=" + currentMatch.getWinnerPlayer());
                try {
                    new FinishedMatchService().save(currentMatch);
                } catch (InternalServerException e) {
                    servletContext.setAttribute("errorMessage", e.getMessage());

                    getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);

                    return;
                }
            }

        }

        resp.sendRedirect("match-score?uuid=" + gameScore.id());
    }
}
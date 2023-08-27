package com.farneser.tennisscoreboard.servlets;

import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatchesService;
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
}
package com.farneser.tennisscoreboard.servlets;

import com.farneser.tennisscoreboard.data.services.MatchFactory;
import com.farneser.tennisscoreboard.data.utils.ParseParamsUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "matches", value = "/matches")
public class MatchesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var matchesDto = ParseParamsUtil.parseGetMatches(req);

        var servletContext = getServletContext();

        servletContext.setAttribute(
                "matches",
                new MatchFactory().findMatch(matchesDto)
        );

        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

}
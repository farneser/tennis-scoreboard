package com.farneser.tennisscoreboard.servlets;

import com.farneser.tennisscoreboard.data.services.hibernate.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "matches", value = "/matches")
public class MatchesServlet extends HttpServlet {
    private final MatchService matchService = new MatchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var servletContext = getServletContext();

        servletContext.setAttribute(
                "matches",
                matchService.get()
        );

        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

}
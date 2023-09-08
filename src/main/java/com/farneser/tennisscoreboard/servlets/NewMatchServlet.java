package com.farneser.tennisscoreboard.servlets;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.exceptons.NotFoundException;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatchesService;
import com.farneser.tennisscoreboard.data.services.hibernate.PlayerService;
import com.farneser.tennisscoreboard.data.utils.ParseParamsUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "new-match", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final CurrentMatchesService currentMatchesService = CurrentMatchesService.getInstance();
    private final PlayerService playerService = new PlayerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var createMatchDto = ParseParamsUtil.ParsePostNewMatch(req);

        var firstPlayer = new Player(createMatchDto.getFirstPlayerName());
        var secondPlayer = new Player(createMatchDto.getSecondPlayerName());

        try {
            firstPlayer = playerService.getByName(firstPlayer.getName());
        } catch (NotFoundException ignored) {
        }

        try {
            secondPlayer = playerService.getByName(secondPlayer.getName());
        } catch (NotFoundException ignored) {
        }

        var id = currentMatchesService.create(
                firstPlayer,
                secondPlayer,
                createMatchDto.getSetsCount()
        );

        resp.sendRedirect("match-score?uuid=" + id);
    }
}
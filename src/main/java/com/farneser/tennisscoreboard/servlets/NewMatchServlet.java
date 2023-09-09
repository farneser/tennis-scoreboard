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
import java.util.logging.Logger;

@WebServlet(name = "new-match", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final CurrentMatchesService currentMatchesService = CurrentMatchesService.getInstance();
    private final PlayerService playerService = new PlayerService();
    private final Logger logger = Logger.getLogger(NewMatchServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var createMatchDto = ParseParamsUtil.ParsePostNewMatch(req);

        var firstPlayer = new Player(createMatchDto.firstPlayerName());
        var secondPlayer = new Player(createMatchDto.secondPlayerName());

        try {
            logger.info("start searching for the first player");
            firstPlayer = playerService.getByName(firstPlayer.getName());
        } catch (NotFoundException ignored) {
            logger.info("first player not found");
        }

        try {
            logger.info("start searching for the second player");
            secondPlayer = playerService.getByName(secondPlayer.getName());
        } catch (NotFoundException ignored) {
            logger.info("second player not found");
        }

        var id = currentMatchesService.create(
                firstPlayer,
                secondPlayer,
                createMatchDto.setsCount()
        );

        resp.sendRedirect("match-score?uuid=" + id);
    }
}
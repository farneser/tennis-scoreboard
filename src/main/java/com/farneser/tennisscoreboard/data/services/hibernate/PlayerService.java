package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.exceptons.NotFoundException;
import com.farneser.tennisscoreboard.data.utils.HibernateFactory;

import java.util.List;
import java.util.logging.Logger;

public class PlayerService extends EntityService<Player> {
    private final Logger logger = Logger.getLogger(PlayerService.class.getName());

    @Override
    public List<Player> get() {
        var session = HibernateFactory.getSessionFactory().openSession();

        logger.info("Started \"FROM PLAYER\" query");

        return session.createQuery("FROM Player", Player.class).getResultList();
    }

    public Player getByName(String name) throws NotFoundException {

        var session = HibernateFactory.getSessionFactory().openSession();

        var queryMessageText = "\"FROM PLAYER\" WHERE lower(name) =" + name.toLowerCase() + " query";

        logger.info("Started " + queryMessageText);

        try {
            return session
                    .createQuery("FROM Player WHERE lower(name) = :name", Player.class)
                    .setParameter("name", name.toLowerCase())
                    .getResultList()
                    .get(0);

        } catch (IndexOutOfBoundsException e) {
            logger.warning("FAILED " + queryMessageText);
            throw new NotFoundException();
        }
    }
}

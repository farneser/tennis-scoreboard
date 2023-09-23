package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.utils.HibernateFactory;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public abstract class EntityService<T> {

    private final Logger logger = Logger.getLogger(EntityService.class.getName());

    public void persist(T object) {

        var message = "create " + object;

        logger.info("starting " + message);

        try (var session = HibernateFactory.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.persist(object);
            transaction.commit();
        } catch (Exception e) {
            logger.warning("failed " + message);
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    public abstract List<T> get();
}

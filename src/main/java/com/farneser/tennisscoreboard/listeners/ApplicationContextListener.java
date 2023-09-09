package com.farneser.tennisscoreboard.listeners;

import com.farneser.tennisscoreboard.data.utils.HibernateFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.logging.Logger;

public class ApplicationContextListener implements ServletContextListener {
    private final Logger logger = Logger.getLogger(ApplicationContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Start");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Starting closing hibernate session");

        HibernateFactory.getSessionFactory().close();

        logger.info("Hibernate session successfully closed");

        logger.info("Destroying the server");
    }
}

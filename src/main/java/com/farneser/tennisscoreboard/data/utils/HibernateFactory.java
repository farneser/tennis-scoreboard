package com.farneser.tennisscoreboard.data.utils;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.logging.Logger;


public class HibernateFactory {

    private final static Logger logger = Logger.getLogger(HibernateFactory.class.getName());
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            logger.info("session factory == null");

            try {

                logger.info("started creating configuration");

                var configuration = new Configuration()
                        .configure("hibernate.cfg.xml");

                logger.info("configuration successfully created");

                logger.info("started creating registry");

                registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .configure()
                        .build();

                logger.info("registry successfully created");

                logger.info("started creating sources");

                var sources = new MetadataSources(registry)
                        .addAnnotatedClass(Player.class)
                        .addAnnotatedClass(Match.class);

                logger.info("sources successfully created");

                logger.info("started creating metadata");

                var metadata = sources
                        .getMetadataBuilder()
                        .build();

                logger.info("metadata successfully created");

                logger.info("started creating session factory");

                sessionFactory = metadata
                        .getSessionFactoryBuilder()
                        .build();

                logger.info("session factory successfully created");

            } catch (Exception e) {

                shutdown();

                logger.warning("failed to create session factory");

                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }

        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {

            logger.info("started destroying registry");

            StandardServiceRegistryBuilder.destroy(registry);

            logger.info("registry successfully destroyed");
        }
    }

}

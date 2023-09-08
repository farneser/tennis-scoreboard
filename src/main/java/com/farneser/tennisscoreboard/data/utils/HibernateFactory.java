package com.farneser.tennisscoreboard.data.utils;

import com.farneser.tennisscoreboard.data.entities.Match;
import com.farneser.tennisscoreboard.data.entities.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateFactory {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                var configuration = new Configuration()
                        .configure("hibernate.cfg.xml");

                registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .configure()
                        .build();
                var sources = new MetadataSources(registry)
                        .addAnnotatedClass(Player.class)
                        .addAnnotatedClass(Match.class);


                var metadata = sources
                        .getMetadataBuilder()
                        .build();

                sessionFactory = metadata
                        .getSessionFactoryBuilder()
                        .build();

            } catch (Exception e) {

                shutdown();

                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}

package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.utils.HibernateFactory;

import java.util.List;

public class PlayerService extends EntityService<Player> {
    public static void main(String[] args) {
        var player = new Player("Andmeow");
        new PlayerService().persist(player);

        var session = HibernateFactory.getSessionFactory().openSession();

        var res = session.createQuery("FROM Player", Player.class).list();

        System.out.println(res);
    }

    @Override
    public List<Player> get() {
        var session = HibernateFactory.getSessionFactory().openSession();

        return session.createQuery("FROM Player", Player.class).getResultList();
    }
}
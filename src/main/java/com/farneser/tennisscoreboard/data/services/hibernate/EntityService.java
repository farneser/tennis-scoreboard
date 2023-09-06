package com.farneser.tennisscoreboard.data.services.hibernate;

import com.farneser.tennisscoreboard.data.utils.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;


public abstract class EntityService<T> {

    public void persist(T object) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(object);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    protected List<T> get(Class<T> type) {
        var session = HibernateFactory.getSessionFactory().openSession();

        return session.createQuery("FROM Player", type).getResultList();
    }

    public abstract List<T> get();
}

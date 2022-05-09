package dao;

import model.Store;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class StoreDao {
    public List<Store> findAll() {
        List<Store> stores = (List<Store>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Store").list();
        return stores;
    }

    public model.Store findStoreById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(model.Store.class, id);
    }

    public void save(model.Store store) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(store);
        transaction.commit();
        session.close();
    }

    public void update(model.Store store) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(store);
        transaction.commit();
        session.close();
    }

    public void delete(model.Store store) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(store);
        transaction.commit();
        session.close();
    }
}

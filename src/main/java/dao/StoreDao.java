package dao;

import model.Store;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
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

    public Store findStoreByName(String name) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Store where name=:name");
        query.setParameter("name", name);
        Store store = (Store) ((org.hibernate.query.Query<?>) query).uniqueResult();
        return store;
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

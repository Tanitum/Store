package dao;

import model.Seller;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class SellerDao {
    public List<Seller> findAll() {
        List<Seller> sellers = (List<Seller>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Seller").list();
        return sellers;
    }

    public model.Seller findSellerById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(model.Seller.class, id);
    }

    public Seller findSellerBySellername(String sellername) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Seller where sellername=:sellername");
        query.setParameter("sellername", sellername);
        Seller seller = (Seller) ((org.hibernate.query.Query<?>) query).uniqueResult();
        return seller;
    }

    public void save(model.Seller seller) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(seller);
        transaction.commit();
        session.close();
    }

    public void update(model.Seller seller) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(seller);
        transaction.commit();
        session.close();
    }

    public void delete(model.Seller seller) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(seller);
        transaction.commit();
        session.close();
    }
}

package dao;

import model.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class ProductsDao {
    public List<Products> findAll() {
        List<Products> products = (List<Products>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Products").list();
        return products;
    }

    public model.Products findProductsById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(model.Products.class, id);
    }

    public List<Products> findProductsByStoreId(int storeid) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Products where storeid=:storeid");
        query.setParameter("storeid", storeid);
        List<Products> products = (List<Products>) ((org.hibernate.query.Query<?>) query).list();
        return products;
    }

    public void save(model.Products products) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(products);
        transaction.commit();
        session.close();
    }

    public void update(model.Products products) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(products);
        transaction.commit();
        session.close();
    }

    public void delete(model.Products products) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(products);
        transaction.commit();
        session.close();
    }
}
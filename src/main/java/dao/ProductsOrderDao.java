package dao;

import java.util.List;

import model.ProductsOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class ProductsOrderDao {
    public List<ProductsOrder> findAll(){
        List<ProductsOrder> productsorders = (List<ProductsOrder>)HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from ProductsOrder").list();
        return productsorders;
    }

    public model.ProductsOrder findProductsOrdersById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(model.ProductsOrder.class, id);
    }

    public void save(model.ProductsOrder productsorders)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(productsorders);
        transaction.commit();
        session.close();
    }

    public void update(model.ProductsOrder productsorders)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(productsorders);
        transaction.commit();
        session.close();
    }

    public void delete(model.ProductsOrder productsorders)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(productsorders);
        transaction.commit();
        session.close();
    }
}
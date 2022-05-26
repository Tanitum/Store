package dao;

import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class OrderDao {
    public List<Order> findAll() {
        List<Order> orders = (List<Order>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Order").list();
        return orders;
    }

    public Order findOrderById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Order.class, id);
    }

    public Order findOrderByNumber(int number) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Order where number=:number");
        query.setParameter("number", number);
        Order order = (Order) ((org.hibernate.query.Query<?>) query).uniqueResult();
        return order;
    }

    public void save(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    public void update(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    public void delete(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
        session.close();
    }
}
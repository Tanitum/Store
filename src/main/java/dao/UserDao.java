package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class UserDao {
    public List<User> findAll() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from User").list();
        return users;
    }

    public model.User findUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(model.User.class, id);
    }

    public User findUserByusername(String username) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from User where username=:username");
        query.setParameter("username", username);
        User user = (User) ((org.hibernate.query.Query<?>) query).uniqueResult();
        return user;
    }

    public void save(model.User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(model.User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(model.User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}

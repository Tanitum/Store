package dao;

import model.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientDao {
    public List<Client> findAll() {
        List<Client> clients = (List<Client>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Client").list();
        return clients;
    }

    public model.Client findClientById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(model.Client.class, id);
    }

    public void save(model.Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    public void update(model.Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(client);
        transaction.commit();
        session.close();
    }

    public void delete(model.Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(client);
        transaction.commit();
        session.close();
    }
}

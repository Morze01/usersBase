package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.SessionFactoryHolder;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;

    public UserDaoHibernateImpl() {
        this.session = SessionFactoryHolder.getInstance().getSessionFactory().openSession();
    }

    @Override
    public User getUserByLogin(String login) {
        Transaction transaction = session.beginTransaction();
        User user = null;
        try {
            Query query = session.createQuery("FROM User where login = :userLogin");
            query.setParameter("userLogin",login);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get User by login: " + e.getMessage());
            transaction.rollback();
        }
        return user;
    }

    @Override
    public Long addUser(User user) {

        Transaction transaction = session.beginTransaction();
        Long userID = null;
        try {
            userID = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t add user: " + e.getMessage());
            transaction.rollback();
        }

        return userID;
    }

    @Override
    public void removeUser(Long id) {

        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM User where id = :userID");
            query.setParameter("userID",id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t delete user: " + e.getMessage());
            transaction.rollback();
        }

    }

    @Override
    public void updateUser(User user) {

        Transaction transaction = session.beginTransaction();

        try {
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t update user: " + e.getMessage());
            transaction.rollback();
        }

    }

    @Override
    public List<User> getAllUsersList() {

        Transaction transaction = session.beginTransaction();
        List<User> userList = null;
        try {
            userList = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t get list of users: " + e.getMessage());
            transaction.rollback();
        }

        return userList;
    }
}

package service;

import dao.AbstractFactoryImpl;
import dao.UserDao;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new AbstractFactoryImpl().getUserDaoFactory().getUserDao();
    private static volatile UserServiceImpl instance;


    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    private UserServiceImpl() {
    }

    @Override
    public User getUserByLogin(String name) {
        return this.userDao.getUserByLogin(name);
    }

    @Override
    public Long addUser(User user) {
        return this.userDao.addUser(user);
    }

    @Override
    public void removeUser(Long id) {
        this.userDao.removeUser(id);
    }

    @Override
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    public List<User> getAllUsersList() {
        return this.userDao.getAllUsersList();
    }
}

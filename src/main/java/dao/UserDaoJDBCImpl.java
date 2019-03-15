package dao;

import executor.Executor;
import executor.UserResultHandler;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = DBHelper.getInstance().getMysqlJDBCConnection();
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return Executor.execQuery(connection,"select * from users t where t.login='" + login + "'", new UserResultHandler()).get(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
//        return Executor.execQuery("select * from users t where t.login='" + login + "'", result -> {
//            if (result.next()) {
//                return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
//            } else {
//                return null;
//            }
//        });
    }

    @Override
    public Long addUser(User user) {
        try {
            createTable();
            Executor.execUpdate(connection, "insert into users (name, password, login) values ('" + user.getName() + "','"
                    + user.getPassword() + "','"
                    + user.getLogin() + "')");
            return getUserByLogin(user.getLogin()).getId();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeUser(Long id) {
        try {
            Executor.execUpdate(connection,"DELETE FROM users WHERE id= '" + id + "'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) {
        try {
            Executor.execUpdate(connection, "UPDATE users SET name = '" + user.getName()
                    + "', password = '" + user.getPassword()
                    + "' , login = '" + user.getLogin()
                    + "' WHERE id=" + user.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsersList() {
        try {
            return Executor.execQuery(connection,"select * from users", new UserResultHandler());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
//        List<User> resultList = new ArrayList<>();
//        return Executor.execQuery("select * from users", result -> {
//            while (result.next()) {
//                resultList.add(new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4)));
//            }
//            return resultList;
//        });
    }

    private void createTable(){
        try {
            Executor.execUpdate(connection,"create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), login varchar(256), primary key (id))");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}

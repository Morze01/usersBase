package dao;


import util.PropertiesReader;

import java.lang.reflect.Constructor;

public class UserDaoFactory {
    public UserDaoFactory() {
    }

    public UserDao getUserDao () {
        String sUserDaoName = PropertiesReader.getProperties("userDao.properties", "userDaoType");
        try {
            Class<?> newClass = Class.forName(sUserDaoName);
            Constructor<?> constructor = newClass.getConstructor();
            return (UserDao) constructor.newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

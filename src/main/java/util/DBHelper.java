package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static volatile DBHelper instance;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public Connection getMysqlJDBCConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_users_base?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Configuration getHibernateConfiguration() {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", PropertiesReader.getProperties("hibernate.properties","dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertiesReader.getProperties("hibernate.properties","driver.class"));
        configuration.setProperty("hibernate.connection.url", PropertiesReader.getProperties("hibernate.properties","connection.url"));
        configuration.setProperty("hibernate.connection.username", PropertiesReader.getProperties("hibernate.properties","username"));
        configuration.setProperty("hibernate.connection.password", PropertiesReader.getProperties("hibernate.properties","password"));
        configuration.setProperty("hibernate.show_sql", PropertiesReader.getProperties("hibernate.properties","show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertiesReader.getProperties("hibernate.properties","hbm2ddl.auto"));
        return configuration;
    }

}

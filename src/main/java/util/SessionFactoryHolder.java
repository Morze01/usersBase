package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryHolder {

    private static volatile SessionFactoryHolder instance;
    private SessionFactory sessionFactory;

    private SessionFactoryHolder() {
        this.sessionFactory = createSessionFactory(DBHelper.getInstance().getHibernateConfiguration());
    }

    public static SessionFactoryHolder getInstance() {
        if (instance == null) {
            synchronized (SessionFactoryHolder.class) {
                if (instance == null) {
                    instance = new SessionFactoryHolder();
                }
            }
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}

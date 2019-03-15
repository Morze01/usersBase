package dao;

public class AbstractFactoryImpl implements AbstractFactory {
    public AbstractFactoryImpl() {
    }

    @Override
    public UserDaoFactory getUserDaoFactory() {
        return new UserDaoFactory();
    }

}

package service;

import model.User;
import java.util.List;

public interface UserService {
    User getUserByLogin(String name);

    Long addUser(User user);

    void removeUser(Long id);

    void updateUser(User user);

    List<User> getAllUsersList();
}

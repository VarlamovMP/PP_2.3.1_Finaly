package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(int id);

    User updateUser(User user);

    User getById(int id);
}

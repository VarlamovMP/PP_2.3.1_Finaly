package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(int id);

    void updateUser(int id, User user);

    User getById(int id);
}

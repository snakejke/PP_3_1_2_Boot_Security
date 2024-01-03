package ru.kata.spring.boot_security.demo.service;


import java.util.List;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {

    List<User> getAllUsersWithRoles();

    User getUserById(Long id);

    void saveUser(User user);

    void deleteUser(Long id);

    User getUserByEmail(String email);

    List<Role> getAllRoles();

}

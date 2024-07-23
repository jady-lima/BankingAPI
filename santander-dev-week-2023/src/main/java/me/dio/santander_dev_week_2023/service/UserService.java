package me.dio.santander_dev_week_2023.service;

import java.util.List;

import me.dio.santander_dev_week_2023.domain.model.User;

public interface UserService {

    List<User> findAllUsers();

    List<User> findUserByName(String name);

    User findUserByUserId(Long id);

    User createUser(User userCreate);

    List<User> createAllUsers(List<User> users);

    User updateUser(Long id, User userUpdate);

    void deleteUser(Long id);
}

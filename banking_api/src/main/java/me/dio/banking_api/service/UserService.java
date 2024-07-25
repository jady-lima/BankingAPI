package me.dio.banking_api.service;

import java.util.List;

import me.dio.banking_api.domain.model.User;

public interface UserService {

    List<User> findAllUsers();

    List<User> findUserByName(String name);

    User findUserByUserId(Long id);

    User createUser(User userCreate);

    List<User> createAllUsers(List<User> users);

    User updateUser(Long id, User userUpdate);

    void deleteUser(Long id);
}

package me.dio.banking_api.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.banking_api.domain.model.User;
import me.dio.banking_api.domain.repository.UserRepository;
import me.dio.banking_api.service.UserService;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> createAllUsers(List<User> users){
        for (User user : users) {
            if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
                throw new IllegalArgumentException("One or more account numbers already exist");
            }            
        }
        return userRepository.saveAll(users);
    }

    @Override
    public User createUser(User userCreate) {
        if(userRepository.existsByAccountNumber(userCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account Number already exists");
        }
        return userRepository.save(userCreate);
    }

    @Override
    public User findUserByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    
    @Override
    public User updateUser(Long id, User userUpdate) {
        User user = findUserByUserId(id);

        user.setName(userUpdate.getName());
        user.setNews(userUpdate.getNews());
        user.setAccount(userUpdate.getAccount());
        user.setCard(userUpdate.getCard());
        user.setFeatures(userUpdate.getFeatures());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}

package me.dio.santander_dev_week_2023.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.santander_dev_week_2023.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByAccountNumber(String accountNumber);
    
    List<User> findByNameContainingIgnoreCase(String name);
}

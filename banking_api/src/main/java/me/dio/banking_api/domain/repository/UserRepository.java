package me.dio.banking_api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.banking_api.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByIdAndFeatures_Id(Long userId, Long featureId);

    boolean existsByIdAndNews_Id(Long userId, Long featureId);
    
    List<User> findByNameContainingIgnoreCase(String name);
}

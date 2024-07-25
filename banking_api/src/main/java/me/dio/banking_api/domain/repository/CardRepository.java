package me.dio.banking_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.banking_api.domain.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{
    
}

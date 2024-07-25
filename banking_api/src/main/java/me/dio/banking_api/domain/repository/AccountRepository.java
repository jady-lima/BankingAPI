package me.dio.banking_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.banking_api.domain.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByNumberContainingIgnoreCase(String accountNumber);
}

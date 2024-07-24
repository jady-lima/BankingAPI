package me.dio.santander_dev_week_2023.service;

import java.util.List;

import me.dio.santander_dev_week_2023.domain.model.Account;

public interface AccountService {
    List<Account> findAllAccounts();

    Account findByAccountId(Long id);

    Account updateAccount(Long id, Account accountUpdate);

    Account findByAccountNumber(String accountNumber);
}
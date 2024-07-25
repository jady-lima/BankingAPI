package me.dio.banking_api.service;

import java.util.List;

import me.dio.banking_api.domain.model.Account;

public interface AccountService {
    List<Account> findAllAccounts();

    Account findByAccountId(Long id);

    Account updateAccount(Long id, Account accountUpdate);

    Account findByAccountNumber(String accountNumber);
}
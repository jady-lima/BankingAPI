package me.dio.banking_api.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.banking_api.domain.model.Account;
import me.dio.banking_api.domain.repository.AccountRepository;
import me.dio.banking_api.service.AccountService;

@Service
public class AccountServiceImp implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByAccountId(Long id) {
        return accountRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByNumberContainingIgnoreCase(accountNumber);
    }


    @Override
    public Account updateAccount(Long id, Account accountUpdate) {
        System.out.println("Account Update: " + accountUpdate);

        Account account = findByAccountId(id);

        if (account != null) {
            account.setAgency(accountUpdate.getAgency());
            account.setBalance(accountUpdate.getBalance());
            account.setLimit(accountUpdate.getLimit());
            account.setNumber(accountUpdate.getNumber());

            return accountRepository.save(account);
        } else {
            throw new NoSuchElementException("Account not found with id: " + id);
        }
    }
    
}

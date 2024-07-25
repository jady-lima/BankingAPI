package me.dio.santander_dev_week_2023.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.santander_dev_week_2023.domain.model.Account;
import me.dio.santander_dev_week_2023.service.AccountService;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Management", description = "Operations pertaining to accounts in the Banking System")
public class AccountController {
 
    @Autowired
    private AccountService accountService;

    @GetMapping
    @Operation(summary = "View a list of all accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        var accounts = accountService.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        var account = accountService.findByAccountId(id);
        return ResponseEntity.ok(account);  
    }

    @PostMapping
    @Operation(summary = "Get account by account number")
    public ResponseEntity<Account> getAccountByNumber(@RequestBody String number){
        Account account = accountService.findByAccountNumber(number);

        if (account == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(account);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account details by ID")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account newDataAccount) {
        System.out.println("Received Account Update: " + newDataAccount);

        try {
            Account updatedAccount = accountService.updateAccount(id, newDataAccount);
            return ResponseEntity.ok(updatedAccount);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.quan.cardadatabase.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.cardadatabase.Model.Account;
import com.quan.cardadatabase.Repository.AccountRepos;
import com.quan.cardadatabase.Service.AccountService;

import lombok.val;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<Account> getAccount(@PathVariable String username) {
        return new ResponseEntity<Account>(accountService.getAccount(username), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@Valid @RequestBody Account account){
        accountService.saveAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/id/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        return new ResponseEntity<>(accountService.updateAccount(id, account), HttpStatus.OK);
    }

    @PutMapping("/toManager/id/{id}")
    public ResponseEntity<Account> updateToManager(@PathVariable Long id) {
        return new ResponseEntity<Account>(accountService.updateToManager(id), HttpStatus.OK);
    }
}

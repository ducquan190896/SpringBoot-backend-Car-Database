package com.quan.cardadatabase.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quan.cardadatabase.Exception.EntityNotFoundException;
import com.quan.cardadatabase.Model.Account;
import com.quan.cardadatabase.Model.RoleType;
import com.quan.cardadatabase.Repository.AccountRepos;
import com.quan.cardadatabase.Repository.RoleRepos;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    AccountRepos accountRepos;
    @Autowired
    RoleRepos roleRepos;

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> entity = accountRepos.findById(id);
        Account account = isCheck(entity, id);
        accountRepos.delete(account);
        
    }

    @Override
    public Account getAccount(String userName) {
        Optional<Account> entity = accountRepos.findByUsername(userName);
        Account account = isCheck(entity, 404L);
        return account;
    }

    @Override
    public List<Account> getAccounts() {
       return accountRepos.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        Optional<Account> entity = accountRepos.findByUsername(account.getUsername());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the account with username " + account.getUsername() + " already exist");
        } else {
            account.setRole(roleRepos.findbyRoleType(RoleType.USER.name()).get());
            account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
            accountRepos.save(account);
        }     
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Optional<Account> entity = accountRepos.findById(id);
        Account account1 = isCheck(entity, id);
        if(!account.getUsername().equals(account1.getUsername())) {
            account1.setUsername(account.getUsername());
        }
        account1.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
       return accountRepos.save(account1);
    }

    @Override
    public Account updateToManager(Long id) {
        Optional<Account> entity = accountRepos.findById(id);
        Account account1 = isCheck(entity, id);
        account1.setRole(roleRepos.findbyRoleType(RoleType.MANAGER.name()).get());
        return accountRepos.save(account1);
    }
    
    private Account isCheck(Optional<Account> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the account with id " + id + " not found");
    }
}

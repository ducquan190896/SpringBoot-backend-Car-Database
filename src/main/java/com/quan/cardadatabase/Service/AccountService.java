package com.quan.cardadatabase.Service;

import java.util.List;

import com.quan.cardadatabase.Model.Account;

public interface AccountService {
    void saveAccount(Account account);
    void deleteAccount(Long id);
    List<Account> getAccounts();
    Account getAccount(String userName);
    Account updateAccount(Long id, Account account); 
    Account updateToManager(Long id);
}

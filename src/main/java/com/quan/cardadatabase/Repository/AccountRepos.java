package com.quan.cardadatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.cardadatabase.Model.Account;

@Repository
public interface AccountRepos extends JpaRepository<Account, Long> {
    
}

package com.quan.cardadatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.cardadatabase.Model.Owner;

@Repository
public interface OwnerRepos extends JpaRepository<Owner, Long> {
    
}

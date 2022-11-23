package com.quan.cardadatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.cardadatabase.Model.Role;

@Repository
public interface RoleRepos extends JpaRepository<Role, Long> {
    
}

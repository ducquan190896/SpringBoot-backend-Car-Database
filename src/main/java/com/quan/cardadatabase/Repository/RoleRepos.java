package com.quan.cardadatabase.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.cardadatabase.Model.Role;

@Repository
public interface RoleRepos extends JpaRepository<Role, Long> {

    @Query(
        value = "select * from role where role_type = ?1",
        nativeQuery = true
    )
    Optional<Role> findbyRoleType(String roleType);
}

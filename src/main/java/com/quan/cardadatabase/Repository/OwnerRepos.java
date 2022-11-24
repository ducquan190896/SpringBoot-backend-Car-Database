package com.quan.cardadatabase.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.cardadatabase.Model.Owner;

@Repository
public interface OwnerRepos extends JpaRepository<Owner, Long> {

    @Query(
        value = "select * from owner where email = ?1",
        nativeQuery = true
    )
    Optional<Owner> findByEmail(String email);
}

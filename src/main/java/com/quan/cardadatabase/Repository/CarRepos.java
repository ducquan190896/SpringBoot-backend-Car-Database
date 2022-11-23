package com.quan.cardadatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.cardadatabase.Model.Car;

@Repository
public interface CarRepos extends JpaRepository<Car, Long> {
    
}

package com.quan.cardadatabase.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.cardadatabase.Model.Car;

@Repository
public interface CarRepos extends JpaRepository<Car, Long> {

    @Query(
        value = "select * from car where car_owner_id = ?1",
        nativeQuery = true
    )
    List<Car> findByCarOwnerId(Long ownerId);
}

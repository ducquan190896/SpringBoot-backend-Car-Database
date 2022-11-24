package com.quan.cardadatabase.Service;

import java.util.List;

import com.quan.cardadatabase.Model.Car;

public interface CarService {
    void saveCar(Car car, Long ownerId);
    void deleteCar(Long id);
    Car getCar(Long id);
    List<Car> getCars();
    Car updateCar(Long id, Car car);
    List<Car> getCarsByOwner(String email);
}

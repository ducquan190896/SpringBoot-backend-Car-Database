package com.quan.cardadatabase.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.cardadatabase.Exception.EntityNotFoundException;
import com.quan.cardadatabase.Model.Car;
import com.quan.cardadatabase.Model.Owner;
import com.quan.cardadatabase.Repository.CarRepos;
import com.quan.cardadatabase.Repository.OwnerRepos;



@Service
public class CarServiceImp implements CarService {

    @Autowired
    CarRepos carRepos;

    @Autowired
    OwnerRepos ownerRepos;

    @Override
    public void deleteCar(Long id) {
        Optional<Car> entity = carRepos.findById(id);
        Car car = isCheck(entity, id);
        carRepos.delete(car);
    }

    @Override
    public Car getCar(Long id) {
       Optional<Car> entity = carRepos.findById(id);
       Car car = isCheck(entity, id);
       return car;
    }

    @Override
    public List<Car> getCars() {
        return carRepos.findAll();
    }

    @Override
    public void saveCar(Car car, Long ownerId) {
        Optional<Owner> entity = ownerRepos.findById(ownerId);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the owner with the id " + ownerId  + " not found");
        }
        Owner owner = entity.get();
        car.setCarOwner(owner);
        carRepos.save(car);
        
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Optional<Car> entity = carRepos.findById(id);
       Car car1 = isCheck(entity, id);
       car1.setBrand(car.getBrand());
       car1.setColor(car.getColor());
       car1.setModel(car.getModel());
       car1.setPrice(car.getPrice());
       car1.setRegisterNumber(car.getRegisterNumber());
       car1.setYear(car.getYear());
      return carRepos.save(car1);
    }

    
    @Override
    public List<Car> getCarsByOwner(String email) {
        Optional<Owner> entity = ownerRepos.findByEmail(email);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the owner with the id " + email  + " not found");
        }
        Owner owner = entity.get();
        List<Car> list = carRepos.findByCarOwnerId(owner.getId());
        return list;

    }

    private Car isCheck(Optional<Car> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new EntityNotFoundException("the car with id " + id  +  " not found");
        }
    }
    
}

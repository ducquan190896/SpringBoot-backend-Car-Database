package com.quan.cardadatabase.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.cardadatabase.Model.Car;
import com.quan.cardadatabase.Service.CarService;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    
    @Autowired
    CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getALl() {
        return new ResponseEntity(carService.getCars(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id ){
        return new ResponseEntity<Car>(carService.getCar(id), HttpStatus.OK);
    }
    @GetMapping("/OwnerEmail/{email}")
    public ResponseEntity<List<Car>> getCarByOwner(@PathVariable String email ){
        return new ResponseEntity<>(carService.getCarsByOwner(email), HttpStatus.OK);
    }

    @PostMapping("/ownerID/{ownerId}")
    public ResponseEntity<HttpStatus> saveCar(@PathVariable Long ownerId, @Valid @RequestBody Car car) {
        carService.saveCar(car, ownerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @Valid @RequestBody Car car) {
        
        return new ResponseEntity<>(carService.updateCar(id, car), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);;
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}

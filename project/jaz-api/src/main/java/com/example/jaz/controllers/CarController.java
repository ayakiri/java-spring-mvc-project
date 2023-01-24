package com.example.jaz.controllers;

import com.example.jaz.exceptions.CarNotFoundException;
import com.example.jaz.exceptions.InvalidCarException;
import com.example.jaz.models.Car;
import com.example.jaz.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {
    private CarService service;

    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/buy/vehicles/cars")
    public ResponseEntity<List<Car>> getCars(){
        List<Car> cars = service.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/buy/vehicles/car/add")
    public ResponseEntity<String> addCar(@RequestBody Car car){

        String name = car.getName();
        String contact = car.getContactInfo();

        if (name.isBlank() || contact.isBlank()){
            throw new InvalidCarException("Fill name and contact info");
        } else {
            service.addCar(car);
            return new ResponseEntity<>(name, HttpStatus.OK);
        }

    }

    @DeleteMapping("/buy/vehicles/car/delete/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){

        Car car = service.deleteCar(id);
        if (car != null){
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            throw new CarNotFoundException("Car id does not exist");
        }
    }
}

package com.example.jaz.services;

import com.example.jaz.models.Car;
import com.example.jaz.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public void addCar(Car car) {
        repository.save(car);
    }

    public Car deleteCar(Long id) {
        Optional<Car> car = repository.findById(id);
        if (car.isPresent()){

            repository.deleteById(id);
            return car.get();
        } else {

            return null;
        }
    }

    public List<Car> findAll() {
        return (List<Car>) repository.findAll();
    }
}

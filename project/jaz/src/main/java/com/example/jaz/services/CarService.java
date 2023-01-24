package com.example.jaz.services;

import com.example.jaz.models.Car;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8081";

    public CarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addCar(Car car) {
        HttpEntity<Car> carHttpEntity= new HttpEntity<>(car);
        restTemplate.postForEntity(apiUrl + "/buy/vehicles/car/add", carHttpEntity, Void.class);
    }

    public void deleteCar(Long id) {
        restTemplate.delete(apiUrl + "/buy/vehicles/car/delete/" + id);
    }

    public List<Car> findAll() {
        ResponseEntity<Car[]> cars = restTemplate.getForEntity(apiUrl + "/buy/vehicles/cars", Car[].class);
        return Arrays.asList(cars.getBody());
    }
}

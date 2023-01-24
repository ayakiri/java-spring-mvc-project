package com.example.jaz.controllers;

import com.example.jaz.models.Car;
import com.example.jaz.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarViewController {

    private final CarService service;

    public CarViewController(CarService service) {
        this.service = service;
    }

    @GetMapping("/buy/vehicles/cars")
    public String showCars(Model model){
        List<Car> cars = service.findAll();
        model.addAttribute("cars", cars);
        return "car/getCars";
    }

    @GetMapping("/buy/vehicles/car/add")
    public String addCarForm(Model model){
        model.addAttribute("car", new Car());
        return "car/addCar";
    }

    @PostMapping("/buy/vehicles/car/add")
    public String addCarSubmit(@ModelAttribute Car car){

        service.addCar(car);
        return "redirect:/buy/vehicles/car/add";
    }

    @GetMapping("/buy/vehicles/car/delete")
    public String deleteCarForm(Model model){
        model.addAttribute("car", new Car());
        return "car/deleteCar";
    }

    @PostMapping("/buy/vehicles/car/delete")
    public String deleteCarSubmit(@ModelAttribute Car car){

        service.deleteCar(car.getId());
        return "redirect:/buy/vehicles/car/delete";
    }
}

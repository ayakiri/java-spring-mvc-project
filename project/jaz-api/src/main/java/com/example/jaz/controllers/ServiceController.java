package com.example.jaz.controllers;

import com.example.jaz.exceptions.InvalidServiceException;
import com.example.jaz.exceptions.ServiceNotFoundException;
import com.example.jaz.models.Service;
import com.example.jaz.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {
    private ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService service){
        this.serviceService = service;
    }

    @GetMapping("/buy/services")
    public ResponseEntity<List<Service>> getServices(){
        List<Service> services = serviceService.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping("/buy/services/add")
    public ResponseEntity<String> addService(@RequestBody Service service){
        String name = service.getName();

        if(name.isBlank()){
            throw new InvalidServiceException("Fill name info");
        } else {
            serviceService.addService(service);
            return new ResponseEntity<>(name, HttpStatus.OK);
        }
    }

    @DeleteMapping("/buy/services/delete/{id}")
    public ResponseEntity<Service> deleteService(@PathVariable Long id){
        Service service = serviceService.deleteService(id);

        if(service != null){
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            throw new ServiceNotFoundException("Service id does not exist");
        }
    }
}

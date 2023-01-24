package com.example.jaz.services;

import com.example.jaz.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    private ServiceRepository repository;

    @Autowired
    public ServiceService(ServiceRepository repository){
        this.repository = repository;
    }

    public List<com.example.jaz.models.Service> findAll(){
        return (List<com.example.jaz.models.Service>) repository.findAll();
    }

    public void addService(com.example.jaz.models.Service service){
        repository.save(service);
    }

    public com.example.jaz.models.Service deleteService(Long id){
        Optional<com.example.jaz.models.Service> service = repository.findById(id);

        if(service.isPresent()){
            repository.deleteById(id);
            return service.get();
        } else {
            return null;
        }
    }
}

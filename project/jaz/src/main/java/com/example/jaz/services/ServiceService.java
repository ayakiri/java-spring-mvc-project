package com.example.jaz.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ServiceService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8081";

    public ServiceService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<com.example.jaz.models.Service> findAll(){
        ResponseEntity<com.example.jaz.models.Service[]> services = restTemplate.getForEntity(apiUrl + "/buy/services", com.example.jaz.models.Service[].class);
        return Arrays.asList(services.getBody());
    }

    public void addService(com.example.jaz.models.Service service){
        HttpEntity<com.example.jaz.models.Service> serviceHttpEntity = new HttpEntity<>(service);
        restTemplate.postForEntity(apiUrl + "/buy/services/add", serviceHttpEntity, Void.class);
    }

    public void deleteService(Long id){
        restTemplate.delete(apiUrl + "/buy/services/delete/" + id);
    }
}

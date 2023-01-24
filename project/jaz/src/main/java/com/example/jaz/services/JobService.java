package com.example.jaz.services;

import com.example.jaz.models.Job;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class JobService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8081";

    public JobService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Job> findAll(){
        ResponseEntity<Job[]> jobs = restTemplate.getForEntity(apiUrl + "/buy/jobs", Job[].class);
        return Arrays.asList(jobs.getBody());
    }

    public void addJob(Job job){
        HttpEntity<Job> jobHttpEntity = new HttpEntity<>(job);
        restTemplate.postForEntity(apiUrl + "/buy/jobs/add", jobHttpEntity, Void.class);
    }

    public void deleteJob(Long id){
        restTemplate.delete(apiUrl + "/buy/jobs/delete/" + id);
    }
}

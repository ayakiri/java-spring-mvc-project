package com.example.jaz.services;

import com.example.jaz.models.Job;
import com.example.jaz.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private JobRepository repository;

    @Autowired
    public JobService(JobRepository repository){
        this.repository = repository;
    }

    public List<Job> findAll(){
        return (List<Job>) repository.findAll();
    }

    public void addJob(Job job){
        repository.save(job);
    }

    public Job deleteJob(Long id){
        Optional<Job> job = repository.findById(id);

        if(job.isPresent()){
            repository.deleteById(id);
            return job.get();
        } else {
            return null;
        }
    }
}

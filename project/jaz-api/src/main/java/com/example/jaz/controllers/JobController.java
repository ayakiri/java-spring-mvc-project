package com.example.jaz.controllers;

import com.example.jaz.exceptions.InvalidJobException;
import com.example.jaz.exceptions.JobNotFoundException;
import com.example.jaz.models.Job;
import com.example.jaz.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private JobService service;

    @Autowired
    public JobController(JobService service){
        this.service = service;
    }

    @GetMapping("/buy/jobs")
    public ResponseEntity<List<Job>> getJobs(){
        List<Job> jobs = service.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping("/buy/jobs/add")
    public ResponseEntity<String> addJob(@RequestBody Job job){
        String name = job.getName();
        String contact = job.getContactInfo();

        if (name.isBlank() || contact.isBlank()){
            throw new InvalidJobException("Fill name and contact info");
        } else {
            service.addJob(job);
            return new ResponseEntity<>(name, HttpStatus.OK);
        }
    }

    @DeleteMapping("/buy/jobs/delete/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable Long id){
        Job job = service.deleteJob(id);

        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            throw new JobNotFoundException("Job id does not exist");
        }
    }
}

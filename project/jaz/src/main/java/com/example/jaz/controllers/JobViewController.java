package com.example.jaz.controllers;

import com.example.jaz.models.Job;
import com.example.jaz.services.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JobViewController {
    private final JobService service;

    public JobViewController(JobService jobService){
        this.service = jobService;
    }

    @GetMapping("/buy/jobs")
    public String showJobs(Model model){
        List<Job> jobs = service.findAll();
        model.addAttribute("jobs", jobs);
        return "job/getJobs";
    }

    @GetMapping("/buy/jobs/add")
    public String addJobForm(Model model){
        model.addAttribute("job", new Job());
        return "job/addJob";
    }

    @PostMapping("/buy/jobs/add")
    public String addJobSubmit(@ModelAttribute Job job){
        service.addJob(job);
        return "redirect:/buy/jobs/add";
    }

    @GetMapping("/buy/jobs/delete")
    public String deleteJobForm(Model model){
        model.addAttribute("job", new Job());
        return "job/deleteJob";
    }

    @PostMapping("/buy/jobs/delete")
    public String deleteJobSubmit(@ModelAttribute Job job){
        service.deleteJob(job.getId());
        return "redirect:/buy/jobs/delete";
    }
}

package com.example.jaz.controllers;

import com.example.jaz.models.Service;
import com.example.jaz.services.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ServiceViewController {
    private final ServiceService serviceService;

    public ServiceViewController(ServiceService serviceService){
        this.serviceService = serviceService;
    }

    @GetMapping("/buy/services")
    public String showServices(Model model){
        List<Service> services = serviceService.findAll();
        model.addAttribute("services", services);
        return "service/getService";
    }

    @GetMapping("/buy/services/add")
    public String addServicesForm(Model model){
        model.addAttribute("service", new Service());
        return "service/addService";
    }

    @PostMapping("/buy/services/add")
    public String addServiceSubmit(@ModelAttribute Service service){
        serviceService.addService(service);
        return "redirect:/buy/services/add";
    }

    @GetMapping("/buy/services/delete")
    public String deleteServiceForm(Model model){
        model.addAttribute("service", new Service());
        return "service/deleteService";
    }

    @PostMapping("/buy/services/delete")
    public String deleteServiceSubmit(@ModelAttribute Service service){
        serviceService.deleteService(service.getId());
        return "redirect:/buy/services/delete";
    }
}

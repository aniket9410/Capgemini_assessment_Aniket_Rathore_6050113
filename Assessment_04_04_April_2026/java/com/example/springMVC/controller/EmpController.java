package com.example.springMVC.controller;



import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.springMVC.dto.EmpDto;
import com.example.springMVC.service.EmpService;

@Controller
public class EmpController {

    private final EmpService service;

    public EmpController(EmpService service) {
        this.service = service;
    }

   
    @GetMapping("/viewall")
    public String viewAll(Model model) {
        model.addAttribute("emps", service.getAll());
        return "view";
    }

    
    @GetMapping("/edit/{eid}")
    public String edit(@PathVariable Integer eid, Model model) {
        model.addAttribute("emp", service.getById(eid));
        return "edit";
    }

    
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("emp") EmpDto dto,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "edit";
        }

        service.save(dto);
        return "redirect:/viewall";
    }


    @GetMapping("/delete/{eid}")
    public String delete(@PathVariable Integer eid) {
        service.delete(eid);
        return "redirect:/viewall";
    }
}
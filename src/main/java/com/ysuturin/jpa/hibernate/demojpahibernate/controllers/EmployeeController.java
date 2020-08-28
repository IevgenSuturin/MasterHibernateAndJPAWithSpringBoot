package com.ysuturin.jpa.hibernate.demojpahibernate.controllers;

import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Employee;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.FullTimeEmployee;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.PartTimeEmployee;
import com.ysuturin.jpa.hibernate.demojpahibernate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping(path = "/employees")
    @ResponseBody
    public List<Employee> getAllEmployees(){
//        return repository.retrieveAllEmployees();
        return repository.retiieveMappedClassAllEmployees();
    }

    @PostMapping(path = "/employees/full-time")
    @ResponseBody
    public String insertEmployee(@RequestBody FullTimeEmployee employee){
        repository.insert(employee);
        return "Employee was inserted";
    }

    @PostMapping(path = "/employees/part-time")
    @ResponseBody
    public String insertEmployee(@RequestBody PartTimeEmployee employee){
        repository.insert(employee);
        return "Employee was inserted";
    }
}

package com.ysuturin.jpa.hibernate.demojpahibernate.controllers;

import com.ysuturin.jpa.hibernate.demojpahibernate.Dto.StudentDto;
import com.ysuturin.jpa.hibernate.demojpahibernate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository dao;

    @GetMapping(path = "/students/create")
    @ResponseBody
    public StudentDto createDefaultStudent(){
        return dao.saveStudentWithPassport();
    }

    @GetMapping(path = "/students/add-default-course")
    @ResponseBody
    public String addDefaultCourse(){
        dao.insertStudentAndCourse();
        return "A student and a course were added";
    }
}

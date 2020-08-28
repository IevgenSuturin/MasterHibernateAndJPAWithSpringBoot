package com.ysuturin.jpa.hibernate.demojpahibernate.controllers;

import com.ysuturin.jpa.hibernate.demojpahibernate.Dto.CourseDto;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;
import com.ysuturin.jpa.hibernate.demojpahibernate.repository.CourseRepository;
import com.ysuturin.jpa.hibernate.demojpahibernate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseRepository dao;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(path = "/courses")
    @ResponseBody
    public List<CourseDto> getAllCoueses(){
        return dao.getAllCourses();
    }

    @GetMapping(path = "/courses/{id}")
    @ResponseBody
    public CourseDto getCourseById(@PathVariable Long id){
        return new CourseDto(dao.findById(id));
    }

    @GetMapping(path = "/courses/add-review")
    @ResponseBody
    public String addReviews(){
        dao.addReviewsForCourse();
        return "Default reviews were added to default course successfully";
    }

    @GetMapping(path = "/courses/add-student")
    @ResponseBody
    public String addDefaultCourse(){
        studentRepository.addStudentToaCourse();
        return "A student was added to a course";
    }

    @DeleteMapping(path = "/courses/{id}")
    @ResponseBody
    public String deleteCourseById(@PathVariable Long id){
        if(dao.deleteById(id)){
            return "The Course was deleted successfully";
        }

        return "The Course was not deleted";
    }

    @PostMapping(path = "/courses")
    @ResponseBody
    public String insertCourse(@RequestBody Course course){
        Course inserted = dao.save(course);
        return "Course id="+inserted.getId()+" was inserted";
    }

    @PutMapping(path = "/courses")
    @ResponseBody
    public String updateCourse(@RequestBody Course course){
        Course updated = dao.save(course);
        return "Course id=" + updated.getId() + " was updated";
    }
}

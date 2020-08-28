package com.ysuturin.jpa.hibernate.demojpahibernate.Dto;

import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;

import java.time.LocalDateTime;

public class CourseDto {
    private Long Id;
    private String name;

    public CourseDto(){}

    public CourseDto(Course course){
        this.Id=course.getId();
        this.name=course.getName();
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}

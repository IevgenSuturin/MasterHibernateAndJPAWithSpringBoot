package com.ysuturin.jpa.hibernate.demojpahibernate.Dto;

import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Student;

public class StudentDto {
    private Long id;
    private String name;

    public StudentDto() {
    }

    public StudentDto(String name) {
        this.name = name;
    }

    public StudentDto(Student student){
        this.id = student.getId();
        this.name = student.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentDto{" + id + ": " + name + '}';
    }
}

package com.ysuturin.jpa.hibernate.demojpahibernate.repository;

import com.ysuturin.jpa.hibernate.demojpahibernate.DemoJpaHibernateApplication;
import com.ysuturin.jpa.hibernate.demojpahibernate.Dto.CourseDto;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Address;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Passport;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateApplication.class)
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void setAddressDetailsForAStudent(){
        Long studentId = 20001L;
        Student student = entityManager.find(Student.class, studentId);
        student.setAddress(new Address("No 101", "Some Street", "Dnipro"));
        entityManager.flush();

        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails(){
        Long studentId = 20001L;
        Student student = entityManager.find(Student.class, studentId);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    public void someTest(){
        repository.someOperationToUderstandPersistenceContext();
    }

    @Test
    @Transactional
    public void retrievePassportAndStudentDetails(){
        Passport passport = entityManager.find(Passport.class, 40001L);
        logger.info("student -> {}", passport);
        logger.info("passport -> {}", passport.getStudent());
    }

    @Test
    @Transactional
    public void retrieveStudentAndCoursesDetails(){
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {} ", student);
        logger.info("Courses -> {} ", student.getCourses());
    }

    @Test
    @Transactional
    public void retrieveCourseAndStudentsDetails(){
        Course course = entityManager.find(Course.class, 10001L);
        logger.info("Course -> {} ", course);
        logger.info("Students -> {} ", course.getStudents());
    }

}

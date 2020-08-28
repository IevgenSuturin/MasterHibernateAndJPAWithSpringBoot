package com.ysuturin.jpa.hibernate.demojpahibernate.repository;

import com.ysuturin.jpa.hibernate.demojpahibernate.Dto.StudentDto;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Passport;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public List<StudentDto> getAllStudents(){
        List<StudentDto> studentsDto = new ArrayList<>();
        TypedQuery<Student> query = em.createQuery("Select s From Student s", Student.class);
        List<Student> students = query.getResultList();
        students.forEach(student -> studentsDto.add(new StudentDto(student)));
        return studentsDto;
    }

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    public void save(Student student){
        if(student.getId() == null){
            em.persist(student);
        } else {
            em.merge(student);
        }
    }

    public boolean deleteById(Long id){
        Student student = findById(id);
        if(student == null) return false;

        em.remove(student);
        return true;
    }

    public StudentDto saveStudentWithPassport(){
        Passport passport = new Passport("Z123456");
        Student student = new Student("Mike");
        student.setPassport(passport);

        em.persist(passport);
        em.persist(student);

        return new StudentDto(student);
    }

    public void someOperationToUderstandPersistenceContext(){
        //Database Operation 1 - Retrieve student
        Long studentId = 20001L;
        Student student = em.find(Student.class, studentId);
        //Persistence Context(student)

        //Database Operation 2 - Retrieve passport
        Passport passport = student.getPassport();
        //Persistence Context(student, passport)

        //Database Operation 3 - update passport
        passport.setNumber("E123457");
        //Persistence Context(student, passport++)

        //Database Operation 4 - update student
        student.setName("Gene - Updated");
        //Persistence Context(student++ , passport++)
    }

    public void insertStudentAndCourse(){
        Student student = new Student("Jak");
        Course course = new Course("Microservices into 100 steps");

        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudents(student);
        em.persist(student);
    }


    public void addStudentToaCourse(){
        Student student = new Student("Jak");
        em.persist(student);

        Course course = em.find(Course.class, 10001L);

        course.addStudents(student);
        student.addCourse(course);
        em.persist(student);
    }
}

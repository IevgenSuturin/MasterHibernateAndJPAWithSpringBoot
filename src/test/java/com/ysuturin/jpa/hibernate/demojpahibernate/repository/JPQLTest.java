package com.ysuturin.jpa.hibernate.demojpahibernate.repository;

import com.ysuturin.jpa.hibernate.demojpahibernate.DemoJpaHibernateApplication;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateApplication.class)
public class JPQLTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic(){
        List resultList = em.createQuery("Select c From Course c").getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_typed(){
        TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
        List<Course> courses = query.getResultList();
        logger.info("Select c From Course c -> {}", courses);
    }

    @Test
    public void jpql_courses_without_students(){
        TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> courses = query.getResultList();
        logger.info("********************************************jpql_courses_without_student log started*************************");
        courses.forEach(course -> logger.info(course.toString()));
    }

    @Test
    public void jpql_courses_with_atleast_2_students(){
        TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> courses = query.getResultList();
        logger.info("********************************************jpql_courses_with_atleast_2_students log started*************************");
        courses.forEach(course -> logger.info(course.toString()));
    }

    @Test
    public void jpql_order_courses_by_number_of_students(){
        TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        List<Course> courses = query.getResultList();
        logger.info("********************************************jpql_order_courses_by_number_of_students log started*************************");
        courses.forEach(course -> logger.info(course.toString()));
    }

    @Test
    public void jpql_students_with_passport_in_a_certain_pattern(){
        TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> courses = query.getResultList();
        logger.info("********************************************jpql_students_with_passport_in_a_certain_pattern log started*************************");
        courses.forEach(course -> logger.info(course.toString()));
    }

    //like
    //between 100 and 200
    //is null
    //upper, lower, trim, length

    //JOIN => Select c, s from Course c JOIN c.students s
    //LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
    //CROSS JOIN => Select c, s from Course c, Student s

    @Test
    public void join(){
        Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size -> {}", resultList.size());
        resultList.forEach(result ->
            logger.info(result[0].toString() + ": " + result[1].toString())
        );
    }

    @Test
    public void left_join(){
        Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size -> {}", resultList.size());
        for (Object[] result: resultList){
         logger.info("Course{} Student{}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join(){
        Query query = em.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size -> {}", resultList.size());
        for (Object[] result: resultList){
         logger.info("Course{} Student{}", result[0], result[1]);
        }
    }

}

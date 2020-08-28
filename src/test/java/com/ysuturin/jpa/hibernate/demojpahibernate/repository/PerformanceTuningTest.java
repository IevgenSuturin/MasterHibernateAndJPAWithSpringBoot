package com.ysuturin.jpa.hibernate.demojpahibernate.repository;

import com.ysuturin.jpa.hibernate.demojpahibernate.DemoJpaHibernateApplication;
import com.ysuturin.jpa.hibernate.demojpahibernate.Dto.CourseDto;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateApplication.class)
public class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void creatingNPlusOneProblem(){
        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        courses.forEach(course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraphSolution(){
        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        courses.forEach(course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetchSolution(){
        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        courses.forEach(course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }
}

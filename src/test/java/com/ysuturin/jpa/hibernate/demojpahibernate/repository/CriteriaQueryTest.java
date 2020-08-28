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
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateApplication.class)
public class CriteriaQueryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    public void criteria_all_courses(){
        //Create query "Select c From Course c" using java API

        //1. Use Criteria Builder to create Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define Predicates etc to the Criteria Builder
        //4. Add Predicates etc to the Criteria Query


        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);

        CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class);
        logger.info("Select from Student -> {}", entityManager.createQuery(studentCriteriaQuery.select(studentCriteriaQuery.from(Student.class))).getResultList());
    }

    @Test
    public void criteria_all_courses_having_100steps_in_them(){
        //Create query "Select c From Course c where name like '%100Steps%'" using java API

        //1. Use Criteria Builder to create Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define Predicates etc to the Criteria Builder
        Predicate like100Steps = criteriaBuilder.like(courseRoot.get("name"), "%100Steps%");

        //4. Add Predicates etc to the Criteria Query
        criteriaQuery.where(like100Steps);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }


    @Test
    public void criteria_all_courses_without_students(){
        //Create query "Select c From Course c where c.students is empty" using java API

        //1. Use Criteria Builder to create Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define Predicates etc to the Criteria Builder
        Predicate studentsIsEmpty = criteriaBuilder.isEmpty(courseRoot.get("students"));

        //4. Add Predicates etc to the Criteria Query
        criteriaQuery.where(studentsIsEmpty);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }


    @Test
    public void criteria_all_courses_joined_to_students(){
        //Create query "Select c From Course c join c.students" using java API

        //1. Use Criteria Builder to create Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define Predicates etc to the Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void criteria_all_courses_left_joined_to_students(){
        //Create query "Select c From Course c left join c.students" using java API

        //1. Use Criteria Builder to create Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define Predicates etc to the Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }
}

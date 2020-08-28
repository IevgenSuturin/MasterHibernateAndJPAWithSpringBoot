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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateApplication.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent(){
        Optional<Course> courseOptional = repository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent(){
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    @DirtiesContext
    public void playingAroundWithSpringRepository(){
        Course course = new Course("Microservices in 100 steps");
        repository.save(course);

        course.setName("Microservices in 100 steps - Updated");
        repository.save(course);

        Optional<Course> courseOptional = repository.findById(course.getId());

        assertEquals(course.getName(), courseOptional.get().getName());

        logger.info("Counts {}", repository.findAll());
        logger.info("Repository count {}", repository.count());
    }

    @Test
    public void sort(){
        Sort sort = Sort.by("name");

        logger.info("Sorted courses {}", repository.findAll(sort));
    }

    @Test
    public void pagination(){
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second page -> {}", secondPage.getContent());

        Pageable thirdPageable = firstPage.nextPageable();
        Page<Course> thirdPage = repository.findAll(thirdPageable);
        logger.info("Third page -> {}", thirdPage.getContent());
    }

    @Test
    public void findByName(){
        logger.info("FindByName -> {}", repository.findByName("JPA in 50 Steps"));
    }
}

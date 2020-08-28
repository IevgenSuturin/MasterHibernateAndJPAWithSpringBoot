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

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateApplication.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findByIdTest_basicTestCase(){
        CourseDto courseDto = new CourseDto(repository.findById(10001L));
        assertEquals("JPA in 50 Steps", courseDto.getName());
    }

    @Test
    @Transactional
    public void findByIdTest_firstLevelCacheDemo(){
        Course course1 = repository.findById(10001L);
        logger.info("First course retrieved {}", course1);

        Course course2 = repository.findById(10001L);
        logger.info("Second course retrieved {}", course2);

        assertEquals("JPA in 50 Steps", course1.getName());
        assertEquals("JPA in 50 Steps", course2.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basicTestCase(){
        Long id = 10002L;
        repository.deleteById(id);
        assertNull(repository.findById(id));
    }

    @Test
    @DirtiesContext
    public void save_insertTesting(){
        Course inserted = new Course("Test course");
        repository.save(inserted);
        assertNotNull(inserted.getId());
    }

    @Test
    @DirtiesContext
    public void save_updateTesting(){
        Long id = 10001L;
        String testName = "Test update";
        Course oldCourse = repository.findById(id);
        assertEquals("JPA in 50 Steps", oldCourse.getName());

        oldCourse.setName(testName);
        repository.save(oldCourse);

        Course updatedCourse = repository.findById(id);
        assertEquals(testName, updatedCourse.getName());

    }

    @Test
    @DirtiesContext
    public void playWithEntityManager(){
        repository.playWithEntityManager();

    }

    @Test
    @Transactional
    public void retrieveReviewForCourse(){
        Course course = repository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview(){
        Review review = entityManager.find(Review.class, 50001L);
        logger.info("Review -> {}", review);
    }
}

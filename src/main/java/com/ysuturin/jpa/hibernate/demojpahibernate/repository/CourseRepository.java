package com.ysuturin.jpa.hibernate.demojpahibernate.repository;

import com.ysuturin.jpa.hibernate.demojpahibernate.Dto.CourseDto;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Review;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public List<CourseDto> getAllCourses(){
//        TypedQuery<Course> allCoursesQuery = em.createQuery("Select c From Course c", Course.class);
        TypedQuery<Course> allCoursesQuery = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> courses = allCoursesQuery.getResultList();
        List<CourseDto> coursesDto = new ArrayList<>();
        courses.forEach( course -> coursesDto.add(new CourseDto(course)));
        return coursesDto;
    }


    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public Course save(Course course){
        if(course.getId() == null){
            //insert
            em.persist(course);
        } else {
            //update
            Course oldCourse = findById(course.getId());
            course.setCreatedTime(oldCourse.getCreatedTime());
            em.merge(course);
        }

        return course;
    }

    public boolean deleteById(Long id){
        boolean result = false;
        Course course = findById(id);
        if(course != null) {
            em.remove(course);
            result=true;
        }

        return result;
    }

    public void playWithEntityManager(){
        logger.info("playWithEntityManager starts");

        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);

        Course course2 = new Course("Angular JS 100 Steps");
        em.persist(course2);

        em.flush();

        course1.setName("Web Services in 100 Steps - Updated");
        course2.setName("Angular JS 100 Steps - Updated");

        em.refresh(course2);

        em.flush();
    }

    public void addReviewsForCourse(){
        //get course 10003
        Long courseId = 10003L;
        Course course = findById(courseId);
        logger.info("course.getReviews -> {}", course.getReviews());

        //add 2 reviews to it
        Review review1 = new Review(ReviewRating.FIVE, "Awesome");
        Review review2 = new Review(ReviewRating.THREE, "Not very good");

        //setting the relationship
        review1.setCourse(course);
        course.addReview(review1);

        review2.setCourse(course);
        course.addReview(review2);

        //save to the database
        em.persist(review1);
        em.persist(review2);
    }
}

package com.ysuturin.jpa.hibernate.demojpahibernate.repository;

import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "jpa-courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
    List<Course> deleteByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> findByNameAndId(String name, Long Id);
    List<Course> countByName(String name);

    @Query("Select c From Course c Where name like '%100%'")
    List<Course> coursesWith100InName();

    @Query(value = "SELECT * FROM course_details WHERE name LIKE '%100%'", nativeQuery = true )
    List<Course> coursesWith100InNameNative();
}

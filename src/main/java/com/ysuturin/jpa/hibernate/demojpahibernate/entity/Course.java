package com.ysuturin.jpa.hibernate.demojpahibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysuturin.jpa.hibernate.demojpahibernate.Dto.CourseDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CourseDetails")
@NamedQueries(
        value = {
                @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
                @NamedQuery(name = "query_get_all_courses_join_fetch", query = "Select c From Course c JOIN FETCH c.students s"),
                @NamedQuery(name = "query_get_all_courses_100_Steps", query = "Select c From Course c where name like '%100 Steps'")
        }
)
@Cacheable
@SQLDelete(sql="update course_details set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
public class Course {

    private static Logger LOGGER = LoggerFactory.getLogger("CourseLogger");

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    private boolean isDeleted;

    @PreRemove
    private void coursePostDelete(){
        isDeleted=true;
        LOGGER.info("COURSE was removed");
    }

    public Course(){}

    public Course(CourseDto courseDto){
        this.name=courseDto.getName();
    }

    public Course(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review){
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" + name + '}';
    }
}

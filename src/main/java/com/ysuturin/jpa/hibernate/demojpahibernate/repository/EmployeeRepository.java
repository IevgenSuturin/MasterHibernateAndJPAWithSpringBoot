package com.ysuturin.jpa.hibernate.demojpahibernate.repository;

import com.ysuturin.jpa.hibernate.demojpahibernate.entity.Employee;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.FullTimeEmployee;
import com.ysuturin.jpa.hibernate.demojpahibernate.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    //insert an employee
    public void insert(Employee employee){
        entityManager.persist(employee);
    }

    //retrieve an employee
    public List<Employee> retrieveAllEmployees(){
       TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e", Employee.class);
       return  query.getResultList();
    }

    public List<Employee> retiieveMappedClassAllEmployees(){
        List<Employee> result = new ArrayList<>();

        entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList().forEach(employee -> result.add(employee));
        entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList().forEach(employee -> result.add(employee));

        return result;
    }

    public Employee findById(Long id){
        return entityManager.find(Employee.class, id);
    }
}

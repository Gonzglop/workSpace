package com.gonzalo.demo.repository;

import java.util.List;

import com.gonzalo.demo.model.Employee;
import com.gonzalo.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee, Long> {
    // select fields from employee where employeeid='[param]'
    Employee findByEmployeeid(String employeeid);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByRole(Role role);

}

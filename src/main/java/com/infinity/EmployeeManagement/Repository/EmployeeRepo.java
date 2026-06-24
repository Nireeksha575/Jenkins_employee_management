package com.infinity.EmployeeManagement.Repository;

import com.infinity.EmployeeManagement.Model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    List<Employee> findByDept(String dept);

    Page<Employee> findByNameContaining(String name, Pageable pageable);

}

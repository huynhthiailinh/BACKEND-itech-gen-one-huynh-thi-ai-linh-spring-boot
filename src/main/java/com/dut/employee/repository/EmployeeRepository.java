package com.dut.employee.repository;

import com.dut.employee.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findEmployeesByName(String name);
    List<Employee> findEmployeesByAge(Long age);
}

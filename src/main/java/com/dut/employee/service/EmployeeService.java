package com.dut.employee.service;

import com.dut.employee.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getEmployees();

    void deleteEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployeesByName(String name);
}

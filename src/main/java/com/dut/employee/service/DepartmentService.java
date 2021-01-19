package com.dut.employee.service;

import com.dut.employee.model.Department;
import com.dut.employee.model.Employee;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);
    List<Department> getDepartments();
}

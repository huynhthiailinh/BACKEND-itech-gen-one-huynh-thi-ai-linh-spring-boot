package com.dut.employee.service;

import com.dut.employee.model.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);

    List<Department> getDepartments();

    Long getCountLikes();
}

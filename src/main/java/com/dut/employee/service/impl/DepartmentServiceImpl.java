package com.dut.employee.service.impl;

import com.dut.employee.model.Department;
import com.dut.employee.repository.DepartmentRepository;
import com.dut.employee.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }
}

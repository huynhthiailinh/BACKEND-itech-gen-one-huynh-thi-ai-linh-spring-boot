package com.dut.employee.controller;

import com.dut.employee.handleJsonFilter.DepartmentFilter;
import com.dut.employee.model.Department;
import com.dut.employee.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentFilter departmentFilter;

    public DepartmentController(DepartmentService departmentService, DepartmentFilter departmentFilter) {
        this.departmentService = departmentService;
        this.departmentFilter = departmentFilter;
    }

    @PostMapping
    public ResponseEntity addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity<>(departmentFilter.getDepartments(departmentService.getDepartments()), HttpStatus.OK);
    }
}

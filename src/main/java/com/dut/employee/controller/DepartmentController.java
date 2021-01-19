package com.dut.employee.controller;

import com.dut.employee.model.Department;
import com.dut.employee.service.DepartmentService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getDepartments() {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(departmentService.getDepartments());
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Department", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAllExcept("id", "department"));
        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }
}

package com.dut.employee.controller;

import com.dut.employee.model.Employee;
import com.dut.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeService.getEmployeeById(id));
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getEmployees() {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeService.getEmployees());
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.CREATED);
    }

    @GetMapping("/age")
    public ResponseEntity getEmployeesByAge(@RequestParam("age") Long age) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeService.getEmployeesByAge(age));
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity getEmployeesByName(@RequestParam("name") String name) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeService.getEmployeesByName(name));
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }
}

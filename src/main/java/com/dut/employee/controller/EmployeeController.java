package com.dut.employee.controller;

import com.dut.employee.handleJsonFilter.EmployeeFilter;
import com.dut.employee.model.Employee;
import com.dut.employee.service.EmployeeService;
import com.dut.employee.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeFilter employeeFilter;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>("Create succeed!", HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeFilter.getEmployee(employeeService.getEmployeeById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getEmployees() {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployees()), HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity getEmployeesByAge(@RequestParam("age") Long age) {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployeesByAge(age)), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity getEmployeesByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployeesByName(name)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity("Delete succeed!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }
}

package com.dut.employee.controller;

import com.dut.employee.constant.DefaultParam;
import com.dut.employee.handleJsonFilter.EmployeeFilter;
import com.dut.employee.model.Employee;
import com.dut.employee.model.EmployeeImage;
import com.dut.employee.repository.EmployeeImageRepository;
import com.dut.employee.service.EmployeeService;
import com.dut.employee.service.ImageService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final EmployeeFilter employeeFilter;

    private final ImageService imageService;

    private final EmployeeImageRepository employeeImageRepository;

    @PostMapping
    public ResponseEntity addEmployee(@RequestParam String employeeString, @RequestParam MultipartFile[] multipartFiles) {
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeString, Employee.class);
        Employee finalEmployee = employeeService.addEmployee(employee);
        Arrays.asList(multipartFiles).stream().forEach(multipartFile -> {
            EmployeeImage employeeImage = new EmployeeImage();
            employeeImage.setEmployee(finalEmployee);
            employeeImage.setUrl(imageService.uploadToLocalFileSystem(multipartFile, DefaultParam.AVATAR, finalEmployee.getId()));
            employeeImageRepository.save(employeeImage);
        });
        return new ResponseEntity<>("Create succeed", HttpStatus.CREATED);
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
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>("Update succeed!", HttpStatus.OK);
    }
}

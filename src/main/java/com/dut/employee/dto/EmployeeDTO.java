package com.dut.employee.dto;

import com.dut.employee.model.Department;
import org.springframework.web.multipart.MultipartFile;

public class EmployeeDTO {
    private Long id;

    private String name;

    private Long age;

    private Long grade;

    private MultipartFile avatar;

    private Department department;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, Long age, Long grade, MultipartFile avatar, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.avatar = avatar;
        this.department = department;
    }

    public EmployeeDTO(String name, Long age, Long grade, MultipartFile avatar, Department department) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.avatar = avatar;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

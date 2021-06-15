package com.dut.employee.repository;

import com.dut.employee.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Long countDepartmentByLikesTrue();
}

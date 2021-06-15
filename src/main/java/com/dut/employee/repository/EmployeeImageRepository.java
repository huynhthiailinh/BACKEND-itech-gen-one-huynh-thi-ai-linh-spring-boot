package com.dut.employee.repository;

import com.dut.employee.model.EmployeeImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeImageRepository extends CrudRepository<EmployeeImage, Integer> {
}

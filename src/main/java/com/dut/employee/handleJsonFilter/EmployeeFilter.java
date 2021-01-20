package com.dut.employee.handleJsonFilter;

import com.dut.employee.model.Employee;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public class EmployeeFilter {
    public EmployeeFilter() {
    }

    public MappingJacksonValue getEmployee(Employee employee) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employee);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    public MappingJacksonValue getEmployees(List<Employee> employeeList) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeList);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}

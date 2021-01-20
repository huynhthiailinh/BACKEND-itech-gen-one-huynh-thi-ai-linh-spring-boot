package com.dut.employee.handleJsonFilter;

import com.dut.employee.model.Department;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentFilter {
    public DepartmentFilter() {
    }

    public MappingJacksonValue getDepartments(List<Department> departmentList) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(departmentList);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Department", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}

package com.employeecatalog.employeesystem.mapper;

import com.employeecatalog.employeesystem.dto.EmployeeDTO;
import com.employeecatalog.employeesystem.model.Employee;

public class EmployeeMapper {
    public static Employee mapToEmployee(EmployeeDTO employeeDTO){
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                employeeDTO.getLocation()
        );
    }

    public static EmployeeDTO mapToEmployeeDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getLocation()
        );
    }
}

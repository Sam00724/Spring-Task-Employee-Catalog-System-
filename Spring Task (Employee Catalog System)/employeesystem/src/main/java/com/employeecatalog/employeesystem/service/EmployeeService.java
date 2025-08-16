package com.employeecatalog.employeesystem.service;

import com.employeecatalog.employeesystem.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<List<EmployeeDTO>> getAllEmployees();
    ResponseEntity<Object> addEmployee(EmployeeDTO employeeDTO);
    ResponseEntity<EmployeeDTO> getEmployeeById(String id);

}

package com.employeecatalog.employeesystem.controller;

import com.employeecatalog.employeesystem.dto.EmployeeDTO;
import com.employeecatalog.employeesystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@org.springframework.web.bind.annotation.RestController
public class RestController {

    EmployeeService employeeService;

    @GetMapping(value = "/displayAll")
    public ResponseEntity<List<EmployeeDTO>> displayAllEmployeesJSON(){
        ResponseEntity<List<EmployeeDTO>> responseEntity = employeeService.getAllEmployees();
        if(responseEntity.getStatusCode() == HttpStatus.NO_CONTENT){
            System.out.println("No data present in the database.");
        }
        return responseEntity;
    }

    @GetMapping(value = "/display/{id}")
    public ResponseEntity<EmployeeDTO> displayEmployeeJSONById(@PathVariable("id") String id){
        ResponseEntity<EmployeeDTO> responseEntity = employeeService.getEmployeeById(id);
        if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT){
            System.out.println("No such employee present in the database.");
        }
        return responseEntity;
    }
}

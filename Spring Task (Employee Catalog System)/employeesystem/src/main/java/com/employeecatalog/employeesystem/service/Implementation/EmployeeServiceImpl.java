package com.employeecatalog.employeesystem.service.Implementation;

import com.employeecatalog.employeesystem.dto.EmployeeDTO;
import com.employeecatalog.employeesystem.mapper.EmployeeMapper;
import com.employeecatalog.employeesystem.model.Employee;
import com.employeecatalog.employeesystem.repo.EmployeeRepository;
import com.employeecatalog.employeesystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee e : employees) {
            employeeDTOs.add(
                    EmployeeMapper.mapToEmployeeDTO(e)
            );
        }
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addEmployee(EmployeeDTO employeeDTO) {

        if(!(getEmployeeById(employeeDTO.getId()).getStatusCode() == HttpStatus.NO_CONTENT)){
            String message = "Employee with an given id already exists. Enter a unique id.";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
            Employee savedEmployee = employeeRepository.save(employee);
            return new ResponseEntity<>(EmployeeMapper.mapToEmployeeDTO(savedEmployee), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findByIdIgnoreCase(id);
        if(optionalEmployee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else{
            EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(optionalEmployee.get());
            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
        }
    }
}

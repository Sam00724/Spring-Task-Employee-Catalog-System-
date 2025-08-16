package com.employeecatalog.employeesystem.repo;

import com.employeecatalog.employeesystem.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findByIdIgnoreCase(String id);

}

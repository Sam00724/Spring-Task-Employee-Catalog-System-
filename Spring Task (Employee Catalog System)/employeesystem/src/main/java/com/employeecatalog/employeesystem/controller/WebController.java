package com.employeecatalog.employeesystem.controller;

import com.employeecatalog.employeesystem.dto.EmployeeDTO;
import com.employeecatalog.employeesystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class WebController {

    EmployeeService employeeService;

    @GetMapping(value = "/error")
    public String handleFallbackError() {
        return "error";
    }

    @GetMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("employeeDTO", new EmployeeDTO());
        return "index";
    }

    @GetMapping(value = "/getAll")
    public String displayAllEmployees(Model model) throws Exception {
        ResponseEntity<List<EmployeeDTO>> responseEntity = employeeService.getAllEmployees();
        if(responseEntity.getStatusCode() == HttpStatus.NO_CONTENT){
            String message = "No data present in the database.";
            System.out.println(message);
            model.addAttribute("dataIsPresent", false);
            model.addAttribute("message", message);
            return "displayAll";
        }
        List<EmployeeDTO> employeeDTOs = responseEntity.getBody();
        model.addAttribute("dataIsPresent", true);
        model.addAttribute("employeeDTOs", employeeDTOs);
        return "displayAll";
    }

    @PostMapping(value = "/addEmployee")
    public String addEmployee(@Valid @ModelAttribute EmployeeDTO employeeDTO, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("hasIssue", true);
            model.addAttribute("message",
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(", ")));
            return "addedEmployee";
        }
        ResponseEntity<Object> responseEntity = employeeService.addEmployee(employeeDTO);
        if(responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
            model.addAttribute("hasIssue", true);
            model.addAttribute("message", responseEntity.getBody());
        } else {
            model.addAttribute("hasIssue", false);
        }
        return "addedEmployee";
    }

    @GetMapping(value = "/getById")
    public String getEmployeeById(@RequestParam String id, Model model){
        ResponseEntity<EmployeeDTO> responseEntity = employeeService.getEmployeeById(id);
        if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT){
            return "userNotFound";
        }
        model.addAttribute("employees",responseEntity.getBody());
        return "displayUserById";
    }

}

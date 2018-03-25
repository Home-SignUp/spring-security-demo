package com.example.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public List<Employee> getEmployees() {
		return employeeService.getAllEmployees();
	}

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") long id) {
		return employeeService.getEmployeeById(id);
	}
}



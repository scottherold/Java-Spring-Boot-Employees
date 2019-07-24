package com.sherold.employees.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sherold.employees.models.Employee;
import com.sherold.employees.services.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeController {
	// <----- Attributes ----->
	// dependency injection
	private EmployeeService employeeService;

	// <----- Constructors ----->
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// <----- Methods ----->
	// Manager finds employees
	@RequestMapping(value="managers", method=RequestMethod.GET, produces="application/json")
	public List<Employee> getEmployees(@RequestParam("id") Long id) {
		return employeeService.findEmployee(id).getEmployees();
	}
	
	// Employee finds manager
	@RequestMapping(value="employees", method=RequestMethod.GET, produces="application/json")
	public Employee getManager(@RequestParam("id") Long id) {
		return employeeService.findEmployee(id).getManager();
	}
	
	// Creates a new employee
	@RequestMapping(value="employees/new", method=RequestMethod.GET, produces="application/json")
	public Employee newEmployee(@RequestParam("firstname") String fName, @RequestParam("lastname") String lName) {
		return employeeService.saveEmployee(new Employee(fName, lName));
	}
	
	// Adds a manager to an employee
	@RequestMapping(value="employees/update/{id}", method=RequestMethod.GET, produces="application/json")
	public Employee addManager(@PathVariable("id") Long eId, @RequestParam("managerid") Long mId) {
		Employee employee = employeeService.findEmployee(eId);
		employee.setManager(employeeService.findEmployee(mId));
		return employeeService.saveEmployee(employee);
	}
}

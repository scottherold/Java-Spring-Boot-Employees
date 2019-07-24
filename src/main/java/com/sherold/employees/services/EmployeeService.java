package com.sherold.employees.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherold.employees.models.Employee;
import com.sherold.employees.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	// <---- Attributes ----->
	// dependency injection
	private EmployeeRepository employeeRepo;
	
	// <----- Constructors ----->
	public EmployeeService(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	// <----- Methods ----->
	// SELECT * FROM employees
	public List<Employee> allEmployees() {
		return employeeRepo.findAll();
	}
	
	// SELECT * FROM employees WHERE id =
	public Employee findEmployee(Long id) {
		// Optional allows for null
		Optional<Employee> optionalEmployee = employeeRepo.findById(id);
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		} else {
			return null;
		}
	}
	
	// INSERT INTO employees
	public Employee saveEmployee(Employee e) {
		return employeeRepo.save(e);
	}
}

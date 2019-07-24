package com.sherold.employees.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.employees.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	// All employees
	List<Employee> findAll();
}

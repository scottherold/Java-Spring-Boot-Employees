package com.sherold.employees.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Designates DB entity
@Table(name="employees")
public class Employee {
	// <----- Attributes ----->
	@Id // designates id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increments field
	private Long id;
	private String first_name;
	private String last_name;
	@Column(updatable=false) // column is immutable after instantiation
	private Date createdAt;
	private Date updatedAt;
	
	// <----- Relationships ----->
	// self-join
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Added to prevent the error from self-join to serialize JSON
	@OneToMany(mappedBy="manager", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Employee> employees;	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="manager_id")
	private Employee manager;

	// <----- Constructors ----->
	public Employee() {
	}
	
	public Employee(String first_name, String last_name) {
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public Employee(String first_name, String last_name, Employee manager) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.manager = manager;
	}

	// <----- Getters/Setters ----->
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Long getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	// <----- Methods ----->
	@PrePersist // run at time of instantiation
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate // run at time of update
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}

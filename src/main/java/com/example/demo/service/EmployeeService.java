package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee createEmployee(Employee employee);
	public Employee getEmployeeById(Long id);
	public Employee save(Long id, Employee employeeDetails);
	public Map<String, Boolean> deleteEmployee(Long id);
	
}

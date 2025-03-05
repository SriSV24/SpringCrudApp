package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployeeById(Long id) {

		Employee employee=null;
		try {
		employee=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id "+id));
		}
		catch(ResourceNotFoundException ex) {
			ex.printStackTrace();
		}
		return employee;
		
	}
	
	public Employee save(Long id, Employee employeeDetails)
	{
		Employee employee=null, updatedEmployee=null;
		try {
		employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id "+id));
		}
		catch(ResourceNotFoundException ex) {
			ex.printStackTrace();
		}
		if (employee != null) {
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		updatedEmployee=employeeRepository.save(employee);
		}
		
		return updatedEmployee;
	}
	
	public Map<String, Boolean> deleteEmployee(Long id) {
		Employee employee=null;
		try {
			employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id "+id));
		}
		catch(ResourceNotFoundException ex) {
			ex.printStackTrace();
		}
		employeeRepository.delete(employee);
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;	
	}
}

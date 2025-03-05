package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.ResourceNotFoundException;
import com.example.demo.service.EmployeeServiceImpl;

@RestController
@RequestMapping(path="/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeServiceImpl.getAllEmployees();
	}
	
	// create employee rest api
	@PostMapping("/createemployee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.createEmployee(employee);
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

		Employee employee=null;
		employee=employeeServiceImpl.getEmployeeById(id);
		return ResponseEntity.ok(employee);
		
	}
	
	// update employee rest api
	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails)
	{
		Employee updatedEmployee=null;
			
		updatedEmployee=employeeServiceImpl.save(id,employeeDetails);
				
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee rest api
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
//		Employee employee=null;
		Map<String, Boolean> response=new HashMap<>();
		response = employeeServiceImpl.deleteEmployee(id);
		return ResponseEntity.ok(response);	
	}
}

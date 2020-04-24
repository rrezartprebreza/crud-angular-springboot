package com.crud.springboot.app.rest;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springboot.app.dao.EmployeeDAO;
import com.crud.springboot.app.entity.Employee;
import com.crud.springboot.app.service.EmployeeService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService){
		
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAll(){
		List<Employee> list = employeeService.findAll();
		return ResponseEntity.ok(list);
	}
	
	//add mapping for GET /employees/{employeedId}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId)
	{
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null){
			throw new RuntimeException("Employee id not found -"+employeeId);
		}
		
		return theEmployee;
		
	}
	
	
	//add mapping for POST /employees - add new employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee TheEmployee){
		// also just in case they pass an id in JSON... set id to 0
		// this is to force to save of new item.... instead of update
		
		TheEmployee.setId(0);
		
		employeeService.save(TheEmployee);
		
		return TheEmployee;
		
	}
	
	//add mapping for put/employees - update existing employee
	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployee(@RequestBody Employee theEmployee){
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	@DeleteMapping("/employees/{employeeId}")
	public String DeleteEmployee(@PathVariable int employeeId){
		Employee tempEmployee = employeeService.findById(employeeId);
		//throw exception if null
		
		if(tempEmployee == null){
			
			throw new RuntimeException("Employee id not foud - " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
		return "Deleted employee id - " + employeeId;
	}
	

}

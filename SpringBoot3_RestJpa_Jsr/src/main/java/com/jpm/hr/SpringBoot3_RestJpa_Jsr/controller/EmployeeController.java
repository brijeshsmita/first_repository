package com.jpm.hr.SpringBoot3_RestJpa_Jsr.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.hr.SpringBoot3_RestJpa_Jsr.entities.Employee;
import com.jpm.hr.SpringBoot3_RestJpa_Jsr.exception.EmployeeException;
import com.jpm.hr.SpringBoot3_RestJpa_Jsr.service.EmployeeService;

//prep-work 1>@CrossOrigin - this annotation enables cross-origin requests for cors issue
@CrossOrigin(origins="*",allowedHeaders="*")
//prep-work 2>@RestController
@RestController
//prep-work 3>@RequestMapping
@RequestMapping("/api/employees")//controller level mapping
public class EmployeeController {
	
	//prep-work 4>@Autowired Employee Service
	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;	
	
	//http://localhost:8082/api/employees
	@GetMapping(value="",produces="application/json")
	public List<Employee> getAllEmployees() throws EmployeeException
	{
		System.out.println("------Inside getAllEmployees-------");
		return employeeService.getEmployeeList();
	}
	//http://localhost:8082/api/employees/getEmployee/1
	@GetMapping(value="getEmployee/{empId}",produces="application/json")
	public Employee getEmployeeById(@PathVariable Long empId) throws EmployeeException
	{
		System.out.println("------Inside getEmployeeById-------");
		return employeeService.getEmployeeById(empId);
	}
	//http://localhost:8082/api/employees/addEmployee/
	@PostMapping(value="/addEmployee")
	public Long addEmployee(@RequestBody Employee employee1) throws EmployeeException
	{
		Long empId = employeeService.addEmployee(employee1);
				
		return empId;
		
	}	
	//http://localhost:8082/api/employees/save/
	@PostMapping("/save")
	public HttpStatus saveEmployee(@RequestBody Employee employee) throws EmployeeException
	{
		return employeeService.addEmployee(employee)!=null?
				HttpStatus.CREATED :HttpStatus.BAD_REQUEST;
	}
	
	
	//http://localhost:8082/api/employees/updateEmployee/10
	
	@PutMapping("updateEmployee/{employeeId}")
	public HttpStatus updateEmployee(@PathVariable(value="employeeId") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws EmployeeException
	{
		Employee employee = employeeService.getEmployeeById(employeeId);
		if(employee == null)
		{
			return HttpStatus.BAD_REQUEST;
		}
		else
		{
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmail(employeeDetails.getEmail());
			employee.setAge(employeeDetails.getAge());
			employee.setDtBirth(employeeDetails.getDtBirth());
			employee.setDtRetire(employeeDetails.getDtRetire());			
			return employeeService.updateEmployee(employee)? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
		}
	}
	
	//http://localhost:8082/api/employees/deleteEmployee/10
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "employeeId") Long employeeId) throws EmployeeException{
		Employee employee = employeeService.getEmployeeById(employeeId);
		Map<String, Boolean> response = new HashMap<>();
		if(employee!= null){
			employeeService.deleteEmployeeById(employeeId);
			response.put("deleted", Boolean.TRUE);
		}
		return response;
	}
	
	
}

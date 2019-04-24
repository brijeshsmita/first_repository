package com.jpm.hr.SpringBoot3_RestJpa_Jsr.service;

import java.util.List;

import com.jpm.hr.SpringBoot3_RestJpa_Jsr.entities.Employee;
import com.jpm.hr.SpringBoot3_RestJpa_Jsr.exception.EmployeeException;

public interface EmployeeService {
	//CRUDS Operation
	public Long addEmployee(Employee employee)throws EmployeeException;//C-create
	public List<Employee> getEmployeeList()throws EmployeeException;//R All Employee -retrieve
	public boolean updateEmployee(Employee employee)throws EmployeeException;//U-update
	public boolean deleteEmployeeById(Long empId)throws EmployeeException;//D-delete
	public Employee getEmployeeById(Long empId)throws EmployeeException;//S-search
}

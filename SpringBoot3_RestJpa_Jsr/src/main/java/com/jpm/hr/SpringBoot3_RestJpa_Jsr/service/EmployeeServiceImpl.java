package com.jpm.hr.SpringBoot3_RestJpa_Jsr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpm.hr.SpringBoot3_RestJpa_Jsr.dao.IEmployeeDao;
import com.jpm.hr.SpringBoot3_RestJpa_Jsr.entities.Employee;
import com.jpm.hr.SpringBoot3_RestJpa_Jsr.exception.EmployeeException;

//prep-work1 -@Service EmployeeService
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	//prep-work2 -@Autowired employeeDao object
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	//prep-work3 - @Transactional for DDL (insert/update/delete)
	@Transactional
	public Long addEmployee(Employee employee) throws EmployeeException {
		return employeeDao.addEmployee(employee);
	}

	@Override
	public List<Employee> getEmployeeList() throws EmployeeException {
		return employeeDao.getEmployeeList();
	}

	@Override
	@Transactional
	public boolean updateEmployee(Employee employee) throws EmployeeException {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	@Transactional
	public boolean deleteEmployeeById(Long empId) throws EmployeeException {
		return employeeDao.deleteEmployeeById(empId);
	}

	@Override
	public Employee getEmployeeById(Long empId) throws EmployeeException {
		return employeeDao.getEmployeeById(empId);
	}

}

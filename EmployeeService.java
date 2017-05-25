package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Employee;

public interface EmployeeService {

	Employee findById(int id);
	
	void save(Employee employee);

	void updateEmployee(Employee employee);
	
	void deleteEmployeeBySsn(String ssn);

	List<Employee> findAllEmployees(); 
	
	Employee findEmployeeByEmployeeId(String employeeId);

	boolean isEmployeeIdUnique(Integer id, String employeeId);
	
	boolean authenticateEmployee(String employeeId,String password);

	boolean isEmployeeExist(Employee employee);
	
	
}

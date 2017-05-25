package com.websystique.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.service.EmployeeService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	EmployeeService service;
	
	@Autowired
	MessageSource messageSource;

	@SuppressWarnings("unused")
	private ObjectError passwordError;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		if(!service.isEmployeeIdUnique(employee.getId(),employee.getEmployeeId())){
			//FieldError employeeIdError=new FieldError("employee","employeeId",messageSource.getMessage("non.unique.employeeId",new String[]{employee.getEmployeeId()},Locale.getDefault()));
			//result.addError(employeeIdError);
			model.addAttribute("try again plz");
			return "registration";
		}

		
		service.save(employee);

		model.addAttribute("success", "Employee " + employee.getFirstName() + " registered successfully");
		//return "redirect:/list";
		return "success";
	}
	
	
	
	/* 
	 This Method is creating for login Employee if Employee has been already Registered
	 */
	
	
  	@RequestMapping(value={"/login"},method=RequestMethod.GET)
	public String loginEmployee(ModelMap model){
		
		model.addAttribute("msg","Please Provide Enter Your login details");
		return "login";
	}   
	
	// checking Authentication of User Name and Password
	
@RequestMapping(value={"/login"},method=RequestMethod.POST)
	
	//public String proccessForm(Model model, @ModelAttribute("Employee")Employee employee, BindingResult result){
public String proccessForm(Model model, HttpServletRequest request){
			String employeeId = request.getParameter("employeeId");
			String password = request.getParameter("password");
			if("concreatepage".equalsIgnoreCase(employeeId)&&"concreatepage".equalsIgnoreCase(password)){
				model.addAttribute("message", "succesfully login");
			}else{
				model.addAttribute("message","Invalid  entry");
			}
			return "redirect:/list";
			
			
	}
  	
	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model) {
		/*Employee employee = service.findEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}*/
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	/*@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}*/

		/*if(!service.isEmployeeSsnUnique(employee.getId(), employee.getPassword())){
			FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getPassword()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}*/

		//service.updateEmployee(employee);

	//	model.addAttribute("success", "Employee " + employee.getFirstName()	+ " updated successfully");
		return "success";
	}/*

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {
		service.deleteEmployeeBySsn(ssn);
		return "redirect:/list";
	}

}

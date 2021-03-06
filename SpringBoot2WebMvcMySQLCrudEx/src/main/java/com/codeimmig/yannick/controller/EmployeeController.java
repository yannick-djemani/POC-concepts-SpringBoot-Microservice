package com.codeimmig.yannick.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeimmig.yannick.exception.EmployeeNotFoundException;
import com.codeimmig.yannick.model.Employee;
import com.codeimmig.yannick.service.IEmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService service;

	private static final Logger LOG=org.slf4j.LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/register")
	public String showReg() {
		return "EmployeeRegister";

	}
	@PostMapping("/save")
	public String saveEmp(@ModelAttribute Employee employee,Model model) {
		try {
			LOG.info("ENTER INTO SAVE METHOD");
			Integer id=service.saveEmployee(employee);
			String message="Employee+'"+id+"'created";
			LOG.debug(message);
			model.addAttribute("message", message);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Problem in save {}",e.getMessage());
		}
		LOG.info("About TO LEAVE SAVE METHOD");
		return "EmployeeRegister";
	}

	@GetMapping("/delete")
	public String deleteById(
			@RequestParam Integer id,
			Model model
			)
	{
		LOG.info("ENTERED INTO DELETE METHOD");
		String message = null;
		try {
			//delete record by id
			service.deleteEmployee(id);
			message = "Employee '"+id+"' Deleted!!";
			LOG.debug(message);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			message = e.getMessage();
			LOG.error("UNABLE TO DELETE {}",message);
		}

		//send message to ui
		model.addAttribute("message", message);
		//also load latest data
		//commonDataFetch(model);
		LOG.info("LEAVING FROM DELETE METHOD");
		return "EmployeeData";
	}

	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model) 
	{
		String page = null;
		try {
			//try to load data from DB
			Employee emp = service.getOneEmployee(id);
			model.addAttribute("employee", emp);
			page = "EmployeeEdit";

		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			page = "EmployeeData";
			//commonDataFetch(model);
			model.addAttribute("message", e.getMessage());
		}

		return page;
	}


	@GetMapping("/all")
	public String getAllEmps(Model model) {
		List<Employee> emps=service.getAllEmployees();
		model.addAttribute("list", emps);
		return "EmployeeData";
	}


}

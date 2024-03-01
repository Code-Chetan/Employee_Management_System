package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.entities.Employee;
import com.myproject.entities.UserInfo;
import com.myproject.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmpController {

	@Autowired
	EmpService empService;
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String home(Model model) {	
		
		List<Employee> allEmployeeList = empService.findAllEmployee();
		model.addAttribute("emp",allEmployeeList);
		
		return "index";
	}
	
	@GetMapping("/add_emp")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String add_emp() {
		return "add_emp";
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String register(@ModelAttribute Employee emp,HttpSession session){
			
		System.out.println(emp);
		empService.add_emp(emp);
		session.setAttribute("msg","Employee addedd successfully");
		
		return "redirect:/employee/";
	}
	
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String editEmployee(@PathVariable int id, Model model) {
		
		Employee employee = empService.findEmpById(id);
		
		model.addAttribute("emp",employee);
		return "edit_emp";
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String updateEmp(@ModelAttribute Employee e,HttpSession session) {
		
		empService.add_emp(e);
		session.setAttribute("msg","Employee data updated successfully");
		return "redirect:/employee/";
	}
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteEmployee(@PathVariable int id,HttpSession session) {
		
		empService.deleteEmployee(id);
		session.setAttribute("msg","Employee deleted successfully");
		return "redirect:/employee/";
	}
	
}

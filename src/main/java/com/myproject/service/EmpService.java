package com.myproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.entities.Employee;
import com.myproject.entities.UserInfo;
import com.myproject.repository.EmpRepo;
import com.myproject.repository.UserInfoRepo;

@Service
public class EmpService {

	@Autowired
	private EmpRepo empRepo;
	
	
	public void add_emp(Employee emp) {
		empRepo.save(emp);
	}
	
	
	public List<Employee> findAllEmployee(){
		
		List<Employee> empList = empRepo.findAll();
		return empList;
	}
	
	
	public Employee findEmpById(int id) {
		
		Optional<Employee> empId = empRepo.findById(id);
		
		if(empId.isPresent()) {
			return empId.get();
		}
		return null;
	}
	
	public void deleteEmployee(int id) {
		
		empRepo.deleteById(id);	
	}
	
}

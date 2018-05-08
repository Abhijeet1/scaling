package upgrad.scaling.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import upgrad.scaling.EmployeeEntityRepository;
import upgrad.scaling.EmployeeService;
import upgrad.scaling.model.Employee;

@RestController
public class RestAPIController {
	
	@Autowired
	EmployeeEntityRepository employeeEntityRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employee/")
    public List<Employee> getAllEmployees(){				 
		long startTime = System.currentTimeMillis();		
		List<Employee> empList = new ArrayList<Employee>();
		employeeEntityRepository.findAll().forEach(empList::add);
		long stopTime = System.currentTimeMillis();
		System.out.println("Time-->" + (stopTime - startTime));
        return empList;
    }
	
	@GetMapping("/employee/{id}")	
	@Cacheable(value = "employee", key="#id")
    public Employee getEmployee(@PathVariable("id") Integer id){				 
		long startTime = System.currentTimeMillis();				
		Employee emp = null;
		List<Employee> empList = new ArrayList<Employee>();
		employeeEntityRepository.findAll().forEach(empList::add);
		
		for(int i = 0 ; i < empList.size() ; i++) {			
			Employee temp = empList.get(i);
			if(temp.getId() == id) {				
				emp = temp;
			}
		}
		long stopTime = System.currentTimeMillis();
		System.out.println("Time---->" + (stopTime - startTime));
        return emp;
    }
	
	@GetMapping("/updatecity/{id}")
	@CachePut(value = "employee", key="#id")
    public Employee updateEmployee(@PathVariable("id") Integer id){				 
		long startTime = System.currentTimeMillis();				
		Employee emp = null;
		List<Employee> empList = new ArrayList<Employee>();
		employeeEntityRepository.findAll().forEach(empList::add);
		
		for(int i = 0 ; i < empList.size() ; i++) {			
			Employee temp = empList.get(i);
			if(temp.getId() == id) {				
				emp = temp;
			}
		}
		emp.setCity("Jaipur");
		employeeEntityRepository.save(emp);
		long stopTime = System.currentTimeMillis();
		System.out.println("Time---->" + (stopTime - startTime));
        return emp;
    }
	
	@GetMapping("/update/")
	public Employee updateAddress(@RequestParam MultiValueMap<String, String> params){
		
		String empId = params.get("empid").get(0);
		String address = params.get("address").get(0);
		Integer id = Integer.parseInt(empId);		
		Employee emp = null;
		List<Employee> empList = new ArrayList<Employee>();
		employeeEntityRepository.findAll().forEach(empList::add);
		
		for(int i = 0 ; i < empList.size() ; i++) {				
			Employee temp = empList.get(i);
			if(temp.getId() == id) {					
				emp = temp;
			}
		}
		
		emp.setAddress(address);
		employeeEntityRepository.save(emp);
		
		return emp;
	}
	
	/*@GetMapping("/updatecity/")
	@CachePut(value = "employee",key="#p0.get(\"empid\").get(0)")
	public Employee updateCity(@RequestParam MultiValueMap<String, String> params){
		
		String empId = params.get("empid").get(0);
		String city = params.get("city").get(0);
		Integer id = Integer.parseInt(empId);		
		Employee emp = null;
		List<Employee> empList = new ArrayList<Employee>();
		employeeEntityRepository.findAll().forEach(empList::add);
		
		for(int i = 0 ; i < empList.size() ; i++) {				
			Employee temp = empList.get(i);
			if(temp.getId() == id) {					
				emp = temp;
			}
		}
		
		emp.setCity(city);
		employeeEntityRepository.save(emp);
		
		return emp;
	}*/
	
	@GetMapping("/save/")
    public String saveEmployees(){				 
		long startTime = System.currentTimeMillis();		
		Employee emp = employeeService.getEmployee(); 
		employeeEntityRepository.save(emp);
		long stopTime = System.currentTimeMillis();
		System.out.println("Saving Time-->" + (stopTime - startTime));
        return "Success";
    }
	
	@GetMapping("/clear/")
	@CacheEvict(value = "employee", allEntries=true)
    public String clearEmployees(){				 
		long startTime = System.currentTimeMillis();		
		
		long stopTime = System.currentTimeMillis();
		System.out.println("Clearing Cache Time-->" + (stopTime - startTime));
        return "Success";
    }
	
	

}

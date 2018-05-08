package upgrad.scaling;

import org.springframework.stereotype.Service;

import upgrad.scaling.model.Employee;

@Service
public class EmployeeService {
	
	public Employee getEmployee(Integer id) {
       
        return new Employee("TheEmployee", "The challenge","Hyd");
    }
	
	public Employee getEmployee() {
	       
        return new Employee("TheEmployee", "The challenge","Hyd");
    }

}

package upgrad.scaling;


import org.springframework.data.repository.CrudRepository;

import upgrad.scaling.model.Employee;

public interface EmployeeEntityRepository  extends CrudRepository<Employee, Integer>  {

}

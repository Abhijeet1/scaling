package upgrad.scaling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Employee(){}
	
	public Employee(int id, String name, String address, String city){
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
	}
	public Employee(String name, String address, String city){
		//this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
	}
	private String name;
	private String address;
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	
}

package com.crud.springboot.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	//define field
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String Lastname;
	@Column(name="email")
	private String email;
	
	// define constructor
	public Employee() {
	
	}

	public Employee(String firstName, String lastname, String email) {
		super();
		this.firstName = firstName;
		Lastname = lastname;
		this.email = email;
	}

	
	
	//define getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	
	//define toString
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", Lastname=" + Lastname + ", email=" + email + "]";
	}
	
	
	

}

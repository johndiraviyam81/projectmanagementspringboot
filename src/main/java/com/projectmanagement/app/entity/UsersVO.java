package com.projectmanagement.app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="employee_id")
	private long employeeId;



	

	public UsersVO() {
		
	}


	

	public long getUserId() {
		return userId;
	}




	public void setUserId(long userId) {
		this.userId = userId;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public long getEmployeeId() {
		return employeeId;
	}




	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}




	@Override
	public String toString() {
		return "UsersVO [userId=" + userId + ", firstName=" + firstName +", lastName=" + lastName +", employeeId=" + employeeId  + "]";
	}

}

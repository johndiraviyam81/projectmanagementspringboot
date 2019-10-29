package com.projectmanagement.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;


public class UserDTO  implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
				
	
	public	String userId;
	public	String firstName;
	public	String lastName;
	public	String employeeId;
	public	String projectId;
	public	String taskId;
	public	String projectName;
	public	String taskName;
	public	String message;
	



		public String getProjectName() {
		return projectName;
	}




	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}




	public String getTaskName() {
		return taskName;
	}




	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}




		public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
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




	public String getEmployeeId() {
		return employeeId;
	}




	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}




	public String getProjectId() {
		return projectId;
	}




	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}




	public String getTaskId() {
		return taskId;
	}




	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




				@Override
		public String toString() {
			return "UserDTO [userId=" + userId  + ", firstName=" + firstName+", lastName=" + lastName +", employeeId=" + employeeId +", projectId=" + projectId +", "
					+ "taskId=" + taskId + ", message=" + message + "]";
		}

	}

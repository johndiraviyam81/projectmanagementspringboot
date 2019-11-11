package com.projectmanagement.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;


public class ProjectDTO  implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
				
	public	String projectId;
	public	String userId;
	public	String userName;
	public	String projectName;
	public	String startDate;
	public	String endDate;
	public	String priority;
	public	String message;
	
		



				public String getProjectId() {
		return projectId;
	}




	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getProjectName() {
		return projectName;
	}




	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}




	public String getStartDate() {
		return startDate;
	}




	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}




	public String getEndDate() {
		return endDate;
	}




	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}




	public String getPriority() {
		return priority;
	}




	public void setPriority(String priority) {
		this.priority = priority;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




				@Override
		public String toString() {
			return "ProjectDTO [projectId=" + projectId + ", projectName=" + projectName + ", startDate=" + startDate + ", endDate=" + endDate
					+ ", priority=" + priority+ ", message=" + message + "]";
		}

	}

package com.projectmanagement.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;


public class ProjectDTO  implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
				
	public	String projectId;
	public	String project;
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




	public String getProject() {
		return project;
	}




	public void setProject(String project) {
		this.project = project;
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
			return "ProjectDTO [projectId=" + projectId + ", project=" + project + ", startDate=" + startDate + ", endDate=" + endDate
					+ ", priority=" + priority+ ", message=" + message + "]";
		}

	}

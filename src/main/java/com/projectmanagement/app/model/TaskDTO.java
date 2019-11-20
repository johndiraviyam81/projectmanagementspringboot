package com.projectmanagement.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;


public class TaskDTO  implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
				
	public	String taskId;
	public	String parentTaskId;
	public	String projectId;
	public	String userId;
	public	String taskName;
	public	String startDate;
	public	String endDate;
	public	String priority;
	public	String status;
	public	String message;
	public	String parentTaskName;
	public	String projectName;
	public	String userName;





				public String getUserId() {
		return userId;
	}





	public void setUserId(String userId) {
		this.userId = userId;
	}





				public String getTaskId() {
		return taskId;
	}





	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}





	public String getParentTaskId() {
		return parentTaskId;
	}





	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}





	public String getProjectId() {
		return projectId;
	}





	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}





	public String getTaskName() {
		return taskName;
	}





	public void setTaskName(String taskName) {
		this.taskName = taskName;
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





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public String getMessage() {
		return message;
	}





	public void setMessage(String message) {
		this.message = message;
	}


	



				public String getParentTaskName() {
		return parentTaskName;
	}





	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}





	public String getProjectName() {
		return projectName;
	}





	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}





	public String getUserName() {
		return userName;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





				@Override
		public String toString() {
			return "TaskDTO [taskId=" + taskId  + ", parentTaskId=" + parentTaskId	+", parentTaskName=" + parentTaskName+", projectName=" + projectName+", userName=" + userName+", taskName=" + taskName +", projectId=" + projectId +", startDate=" + startDate +", "
					+ "endDate=" + endDate + ", priority=" + priority +  ", status=" + status + "]";
		}

	}

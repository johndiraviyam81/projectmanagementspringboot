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
	public	String task;
	public	String startDate;
	public	String endDate;
	public	String priority;
	public	String status;
	public	String message;
	




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





	public String getTask() {
		return task;
	}





	public void setTask(String task) {
		this.task = task;
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





				@Override
		public String toString() {
			return "TaskDTO [taskId=" + taskId  + ", parentTaskId=" + parentTaskId+", task=" + task +", projectId=" + projectId +", startDate=" + startDate +", "
					+ "endDate=" + endDate + ", priority=" + priority +  ", status=" + status + "]";
		}

	}

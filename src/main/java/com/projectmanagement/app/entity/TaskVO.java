package com.projectmanagement.app.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "task")
public class TaskVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private	long taskId;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private ParentTaskVO parentTaskVO;

	@ManyToOne
	@JoinColumn(name="project_id")
	private ProjectVO projectVO;
	

	@Column(name="task")
	protected String task;
	
	@Column(name="start_date")
	protected LocalDate startDate;
	
	@Column(name="end_date")
	protected LocalDate endDate;
	
	@Column(name="priority")
	protected int priority;
	
	@Column(name="status")
	protected String status;

	public TaskVO() {
		
	}
	
	

	
	public long getTaskId() {
		return taskId;
	}




	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}




	public ParentTaskVO getParentTaskVO() {
		return parentTaskVO;
	}




	public void setParentTaskVO(ParentTaskVO parentTaskVO) {
		this.parentTaskVO = parentTaskVO;
	}




	public ProjectVO getProjectVO() {
		return projectVO;
	}




	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}




	public String getTask() {
		return task;
	}




	public void setTask(String task) {
		this.task = task;
	}




	public LocalDate getStartDate() {
		return startDate;
	}




	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}




	public LocalDate getEndDate() {
		return endDate;
	}




	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}




	public int getPriority() {
		return priority;
	}




	public void setPriority(int priority) {
		this.priority = priority;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "TaskVO [taskId=" + taskId  + ", task=" + task + ", parentTaskVO=" + parentTaskVO +", projectVO=" + projectVO +", startDate=" + startDate +", endDate=" + endDate + ", priority=" + priority +  ", status=" + status + "]";
	}

}

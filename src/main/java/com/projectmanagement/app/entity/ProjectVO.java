package com.projectmanagement.app.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class ProjectVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="project_id")
	protected	long projectId;

	@Column(name="project")
	protected String project;

	@Column(name="start_date")
	protected LocalDate startDate;
	
	@Column(name="end_date")
	protected LocalDate endDate;

	@Column(name="priority")
	protected int priority;
	
	public ProjectVO() {
		
	}

	
	
	
	public long getProjectId() {
		return projectId;
	}




	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}




	public String getProject() {
		return project;
	}




	public void setProject(String project) {
		this.project = project;
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




	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", project=" + project +  ", startDate=" + startDate +", endDate=" + endDate + ", priority=" + priority + "]";
	}

}

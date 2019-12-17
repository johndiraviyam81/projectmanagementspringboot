/*
 * 
 */
package com.projectmanagement.app.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class ProjectVO.
 */
@Entity
@Table(name = "project")
public class ProjectVO {

	/** The project id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	protected long projectId;

	/** The project. */
	@Column(name = "project")
	protected String project;

	/** The start date. */
	@Column(name = "start_date")
	protected LocalDate startDate;

	/** The end date. */
	@Column(name = "end_date")
	protected LocalDate endDate;

	/** The task list. */
	@OneToMany(mappedBy = "projectVO")
	private List<TaskVO> taskList = new ArrayList<>();

	/** The priority. */
	@Column(name = "priority")
	protected int priority;

	/**
	 * Gets the task list.
	 *
	 * @return the task list
	 */
	public List<TaskVO> getTaskList() {
		return taskList;
	}

	/**
	 * Sets the task list.
	 *
	 * @param taskList the new task list
	 */
	public void setTaskList(List<TaskVO> taskList) {
		this.taskList = taskList;
	}

	/** The users VO. */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersVO usersVO;

	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public long getProjectId() {
		return projectId;
	}

	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Gets the users VO.
	 *
	 * @return the users VO
	 */
	public UsersVO getUsersVO() {
		return usersVO;
	}

	/**
	 * Sets the users VO.
	 *
	 * @param usersVO the new users VO
	 */
	public void setUsersVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		ProjectVO projectVO = (ProjectVO) o;
		return Objects.equals(project, projectVO.project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(project);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectVO [projectId=" + projectId + ", project=" + project + ", startDate=" + startDate + ", endDate="
				+ endDate + ", priority=" + priority + ", usersVO=" + usersVO + "]";
	}

}

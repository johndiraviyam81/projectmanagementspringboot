/*
 * 
 */
package com.projectmanagement.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;


// TODO: Auto-generated Javadoc
/**
 * The Class ProjectDTO.
 */
public class ProjectDTO  implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
				
	/** The project id. */
	public	String projectId;
	
	/** The user id. */
	public	String userId;
	
	/** The user name. */
	public	String userName;
	
	/** The project name. */
	public	String projectName;
	
	/** The start date. */
	public	String startDate;
	
	/** The end date. */
	public	String endDate;
	
	/** The priority. */
	public	String priority;
	
	/** The message. */
	public	String message;
	
		



				/**
				 * Gets the project id.
				 *
				 * @return the project id
				 */
				public String getProjectId() {
		return projectId;
	}




	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}




	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}




	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}




	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}




	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}




	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}




	/**
	 * Sets the project name.
	 *
	 * @param projectName the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}




	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}




	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}




	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}




	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}




	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}




	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}




	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}




	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}




				/* (non-Javadoc)
				 * @see java.lang.Object#toString()
				 */
				@Override
		public String toString() {
			return "ProjectDTO [projectId=" + projectId + ", projectName=" + projectName + ", startDate=" + startDate + ", endDate=" + endDate
					+ ", priority=" + priority+ ", message=" + message + "]";
		}

	}

/*
 * 
 */
package com.projectmanagement.app.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class ParentTaskVO.
 */
@Entity
@Table(name = "parent_task")
public class ParentTaskVO {
	
	/** The parent id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parent_id")
	protected	long parentId;
	
	/** The parent task. */
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="task_id")
	private TaskVO parentTask;
	
	/**
	 * Instantiates a new parent task VO.
	 */
	public ParentTaskVO()
	{
		
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public long getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the parent task.
	 *
	 * @return the parent task
	 */
	public TaskVO getParentTask() {
		return parentTask;
	}

	/**
	 * Sets the parent task.
	 *
	 * @param parentTask the new parent task
	 */
	public void setParentTask(TaskVO parentTask) {
		this.parentTask = parentTask;
	}
	
	 /* (non-Javadoc)
 	 * @see java.lang.Object#equals(java.lang.Object)
 	 */
 	@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        ParentTaskVO parentTaskVO = (ParentTaskVO) o;
	        return Objects.equals(parentTask, parentTaskVO.parentTask);
	    }

	    /* (non-Javadoc)
    	 * @see java.lang.Object#hashCode()
    	 */
    	@Override
	    public int hashCode() {
	        return Objects.hash(parentTask);
	    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParentTaskVO [parentId=" + parentId  + ", parentTask=" + parentTask.toString() + "]";
	}

}

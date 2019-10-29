package com.projectmanagement.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class ParentTaskVO {
	
	@Id
	@Column(name="Parent_Id")
	protected	long parentId;
	
	@Column(name="Parent_Task")
	protected String parentTask;
	
	public ParentTaskVO()
	{
		
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
	@Override
	public String toString() {
		return "ParentTaskVO [parentId=" + parentId  + ", parentTask=" + parentTask + "]";
	}

}

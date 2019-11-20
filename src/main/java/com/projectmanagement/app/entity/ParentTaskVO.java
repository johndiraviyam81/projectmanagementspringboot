package com.projectmanagement.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class ParentTaskVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Parent_Id")
	protected	long parentId;
	
	@OneToOne
	@JoinColumn(name="Parent_Task")
	private TaskVO parentTask;
	
	public ParentTaskVO()
	{
		
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public TaskVO getParentTask() {
		return parentTask;
	}

	public void setParentTask(TaskVO parentTask) {
		this.parentTask = parentTask;
	}
	
	@Override
	public String toString() {
		return "ParentTaskVO [parentId=" + parentId  + ", parentTask=" + parentTask.toString() + "]";
	}

}

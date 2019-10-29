package com.projectmanagement.app.service;

import java.util.List;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.model.TaskDTO;





public interface TasksService 
{
	public long save(TaskDTO taskDTO) throws Exception;

	public List<TaskDTO> getAllTasks();


}

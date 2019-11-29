package com.projectmanagement.app.service;

import java.sql.SQLException;
import java.util.List;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.model.TaskDTO;





// TODO: Auto-generated Javadoc
/**
 * The Interface TasksService.
 */
public interface TasksService 
{
	
	/**
	 * Save.
	 *
	 * @param taskDTO the task DTO
	 * @return the long
	 * @throws Exception the exception
	 */
	public long save(TaskDTO taskDTO) throws Exception;

	/**
	 * Gets the all tasks.
	 *
	 * @return the all tasks
	 */
	public List<TaskDTO> getAllTasks() throws Exception; 
	
	/**
	 * Gets the task by id.
	 *
	 * @param taskId the task id
	 * @return the task by id
	 */
	public TaskDTO getTaskById(long taskId) throws Exception;
	
	/**
	 * Gets the task by parent id.
	 *
	 * @param parentById the parent by id
	 * @return the task by parent id
	 */
	public TaskDTO getTaskByParentId(long parentById) throws Exception;
	
	/**
	 * Search tasks.
	 *
	 * @param taskName the task name
	 * @return the list
	 */
	public List<TaskDTO> searchTasks(String taskName) throws Exception;
	
	/**
	 * Delete by task by id.
	 *
	 * @param taskId the task id
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean deleteByTaskById(long taskId) throws Exception;


}

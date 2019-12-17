package com.projectmanagement.app.service;

import java.sql.SQLException;
import java.util.List;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.model.TaskDTO;

/**
 * The Interface TasksService.
 */
public interface TasksService {

	/**
	 * Save.
	 *
	 * @param taskDTO
	 *            the task DTO
	 * @return the long
	 * @throws Exception
	 *             the exception
	 */
	public long save(TaskDTO taskDTO) throws Exception;

	/**
	 * Gets the all tasks.
	 *
	 * @return the all tasks
	 * @throws Exception the exception
	 */
	public List<TaskDTO> getAllTasks() throws Exception;

	/**
	 * Gets the all parent tasks.
	 *
	 * @return the all parent tasks
	 * @throws Exception the exception
	 */
	public List<TaskDTO> getAllParentTasks() throws Exception;

	/**
	 * Gets the task by id.
	 *
	 * @param taskId            the task id
	 * @return the task by id
	 * @throws Exception the exception
	 */
	public TaskDTO getTaskById(long taskId) throws Exception;

	/**
	 * Save parent task.
	 *
	 * @param taskDTO the task DTO
	 * @return the long
	 * @throws Exception the exception
	 */
	public long saveParentTask(TaskDTO taskDTO) throws Exception;

	/**
	 * Search tasks.
	 *
	 * @param projectName the project name
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TaskDTO> searchTasks(String projectName) throws Exception;

	/**
	 * Delete by task by id.
	 *
	 * @param taskId            the task id
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean deleteByTaskById(long taskId) throws Exception;

}

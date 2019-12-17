package com.projectmanagement.app.service;

import java.sql.SQLException;
import java.util.List;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.model.ProjectDTO;


/**
 * The Interface ProjectsService.
 */
public interface ProjectsService {

	/**
	 * Save.
	 *
	 * @param projectdto
	 *            the projectdto
	 * @return the long
	 * @throws Exception
	 *             the exception
	 */
	public long save(ProjectDTO projectdto) throws Exception;

	/**
	 * Gets the all projects.
	 *
	 * @return the all projects
	 * @throws Exception the exception
	 */
	public List<ProjectDTO> getAllProjects() throws Exception;

	/**
	 * Search projects.
	 *
	 * @param projectContain            the project contain
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ProjectDTO> searchProjects(String projectContain) throws Exception;

	/**
	 * Gets the project by id.
	 *
	 * @param projectId            the project id
	 * @return the project by id
	 * @throws Exception the exception
	 */
	public ProjectDTO getProjectById(long projectId) throws Exception;

	/**
	 * Delete project by id.
	 *
	 * @param projectId            the project id
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean deleteProjectById(long projectId) throws Exception;

}

package com.projectmanagement.app.service;

import java.util.List;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.model.ProjectDTO;





public interface ProjectsService 
{
	public long save(ProjectDTO projectdto) throws Exception;

	public List<ProjectDTO> getAllProjects();
	
	public List<ProjectDTO> searchProjects(String projectContain);

	public ProjectDTO getProjectById(long projectId);

}

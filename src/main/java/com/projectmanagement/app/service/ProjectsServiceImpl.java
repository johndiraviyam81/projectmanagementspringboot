package com.projectmanagement.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.repositories.ProjectVORepository;
import com.projectmanagement.app.model.ProjectDTO;






@Service
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	ProjectVORepository projectVORepository;
	
	
	
	@Override
	@Transactional
	public List<ProjectDTO> getAllProjects() 
	{
		
		List<ProjectDTO> allProjects=new ArrayList<ProjectDTO>();
		
		List<ProjectVO> projectList=new ArrayList<ProjectVO>();
		try
		{
			projectList= projectVORepository.findAll();
		 if(projectList!=null && projectList.size()>0)
		 {
			for(ProjectVO projectVO : projectList) 
			{
				System.out.print(projectVO.toString());
				allProjects.add(mapProjectDto(projectVO));				
			}
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
					
			return allProjects;
	}

	@Override
	@Transactional
	public long save(ProjectDTO projectDTO) throws Exception {
	
	long projectId=0L;
	
	ProjectVO projectVO=projectVORepository.save(mapProjectVo(projectDTO));
	if(projectVO!=null && projectVO.getProjectId()!=0)
		projectId=projectVO.getProjectId();
	return projectId;
	}

	private ProjectVO mapProjectVo(ProjectDTO projectDTO)
	{
		ProjectVO projectVo=new ProjectVO();
		System.out.println("******* projectDTO object the  **********\n"+projectDTO.toString());
		projectVo.setProject(projectDTO.getProject());
		if(projectDTO.getProjectId()!=null && !projectDTO.getProjectId().isEmpty())
		projectVo.setProjectId(Long.parseLong(projectDTO.getProjectId()));
		
		projectVo.setStartDate(LocalDate.parse(projectDTO.getStartDate()));
		projectVo.setEndDate(LocalDate.parse(projectDTO.getEndDate()));
		projectVo.setPriority(Integer.parseInt(projectDTO.getPriority()));
		
		return projectVo;
	}
	
	private ProjectDTO mapProjectDto(ProjectVO projectVO)
	{
		ProjectDTO projectDTO=new ProjectDTO();
		System.out.println("******* projectVO object the  **********\n"+projectVO.toString());
		projectDTO.setProjectId(String.valueOf(projectVO.getProjectId()));
		projectDTO.setProject(projectVO.getProject());
		projectDTO.setStartDate(String.valueOf(projectVO.getStartDate()));
		projectDTO.setEndDate(String.valueOf(projectVO.getEndDate()));
		projectDTO.setPriority(String.valueOf(projectVO.getPriority()));
		
		return projectDTO;
	}
}

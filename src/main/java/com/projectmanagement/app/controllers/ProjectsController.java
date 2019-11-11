package com.projectmanagement.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.app.model.ProjectDTO;
import com.projectmanagement.app.service.ProjectsService;
import com.projectmanagement.app.entity.ProjectVO;




@RestController
@RequestMapping("/projects")
public class ProjectsController {


	
	@Autowired
	private	ProjectsService projectsService;
	
	
	
	@CrossOrigin
	@GetMapping(value = "/allprojects")
	public ResponseEntity<List<ProjectDTO>> getAllProjects()
	{
		List<ProjectDTO> projectList=new ArrayList<ProjectDTO>();
		try
		{		
			projectList=projectsService.getAllProjects();
			
			return ResponseEntity.ok().body(projectList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return ResponseEntity.badRequest().body(projectList);
		
	}
	
	@CrossOrigin
	@PostMapping(value = "/search")
	public ResponseEntity<List<ProjectDTO>> searchAllProjects(@RequestBody String projectName)
	{
		List<ProjectDTO> projectList=new ArrayList<ProjectDTO>();
		try
		{		
			projectList=projectsService.searchProjects(projectName);
			
			return ResponseEntity.ok().body(projectList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return ResponseEntity.badRequest().body(projectList);
		
	}
	
	
	@CrossOrigin
	@PostMapping(value = "/add")
	public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDTO)
	{
		String message="Project is added successfully";
		String badRequestMessage="Error has been occured while creating project";
		long projectId=0L;		
		try
		{	
			projectId=projectsService.save(projectDTO);
			 if(projectId>0L)
			 {
			 projectDTO.setMessage(message);			
			 }
			 else
			 {
				projectDTO.setMessage(badRequestMessage);
				
			 }
			 return ResponseEntity.ok().body(projectDTO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			projectDTO.setMessage(badRequestMessage);
			return ResponseEntity.badRequest().body(projectDTO);
		}
				
	}
		
	@CrossOrigin
	@GetMapping(value = "/project/{projectId}")
	public ResponseEntity<ProjectDTO> getProject(@PathVariable("projectId")String projectId)
	{
		String message="Project is updated successfully";
		String badRequestMessage="Error has been occured while creating project";
		ProjectDTO projectDTO=new ProjectDTO();		
		try
		{	
			projectDTO=projectsService.getProjectById(Long.parseLong(projectId));
			 if(!projectDTO.getProjectId().isEmpty())
			 {
			 projectDTO.setMessage(message);			
			 }
			 else
			 {
				projectDTO.setMessage(badRequestMessage);
				
			 }
			 return ResponseEntity.ok().body(projectDTO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			projectDTO.setMessage(badRequestMessage);
			return ResponseEntity.badRequest().body(projectDTO);
		}
				
	}
	
	@CrossOrigin
	@PutMapping(value = "/project")
	public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO)
	{
		String message="Project is updated successfully";
		String badRequestMessage="Error has been occured while creating project";
		long projectId=0L;		
		try
		{	
			projectId=projectsService.save(projectDTO);
			 if(projectId>0L)
			 {
			 projectDTO.setMessage(message);			
			 }
			 else
			 {
				projectDTO.setMessage(badRequestMessage);
				
			 }
			 return ResponseEntity.ok().body(projectDTO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			projectDTO.setMessage(badRequestMessage);
			return ResponseEntity.badRequest().body(projectDTO);
		}
				
	}


}

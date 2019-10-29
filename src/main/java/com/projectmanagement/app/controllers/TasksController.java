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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.app.model.TaskDTO;
import com.projectmanagement.app.service.TasksService;
import com.projectmanagement.app.entity.TaskVO;




@RestController
@RequestMapping("/tasks")
public class TasksController {


	
	@Autowired
	private	TasksService tasksService;
	
	
	
	@CrossOrigin
	@GetMapping(value = "/alltasks")
	public ResponseEntity<List<TaskDTO>> getAllTasks()
	{
		List<TaskDTO> taskList=new ArrayList<TaskDTO>();
		try
		{		
			taskList=tasksService.getAllTasks();
			
			return ResponseEntity.ok().body(taskList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return ResponseEntity.badRequest().body(taskList);
		
	}
	
	@CrossOrigin
	@PostMapping(value = "/add")
	public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO)
	{
		String message="Task is added successfully";
		String badRequestMessage="Error has been occured while creating task";
		long taskId=0L;		
		try
		{	
			taskId=tasksService.save(taskDTO);
			 if(taskId>0L)
			 {
				 taskDTO.setMessage(message);
			 return ResponseEntity.ok().body(taskDTO);
			 }
			 else
			 {
				 taskDTO.setMessage(badRequestMessage);
				return ResponseEntity.badRequest().body(taskDTO); 
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			taskDTO.setMessage(badRequestMessage);
			return ResponseEntity.badRequest().body(taskDTO);
		}
				
	}
		


}

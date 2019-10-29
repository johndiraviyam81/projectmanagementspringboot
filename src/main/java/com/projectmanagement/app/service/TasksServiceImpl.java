package com.projectmanagement.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.projectmanagement.app.entity.TaskVO;
import com.projectmanagement.app.entity.ParentTaskVO;
import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.repositories.TaskVORepository;
import com.projectmanagement.app.repositories.ParentTaskVORepository;
import com.projectmanagement.app.repositories.ProjectVORepository;
import com.projectmanagement.app.model.TaskDTO;






@Service
public class TasksServiceImpl implements TasksService {

	@Autowired
	TaskVORepository taskVORepository;
	
	@Autowired
	ParentTaskVORepository parentTaskVORepository;
	
	@Autowired
	ProjectVORepository projectVORepository;
	
	@Override
	@Transactional
	public List<TaskDTO> getAllTasks() 
	{
		
		List<TaskDTO> allTasks=new ArrayList<TaskDTO>();
		
		List<TaskVO> taskList=new ArrayList<TaskVO>();
		try
		{
			taskList= taskVORepository.findAll();
		 if(taskList!=null && taskList.size()>0)
		 {
			for(TaskVO taskVO : taskList) 
			{
				System.out.print(taskVO.toString());
				allTasks.add(mapTaskDto(taskVO));				
			}
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
					
			return allTasks;
	}

	@Override
	@Transactional
	public long save(TaskDTO taskDTO) throws Exception {
	
	long taskId=0L;
	TaskVO parentCreateTaskVO=null;
	ParentTaskVO  patentTaskVO=new ParentTaskVO();
	ProjectVO projectVO=new ProjectVO();
	TaskVO createTaskVO=mapTaskVo(taskDTO);
	if(taskDTO!=null && taskDTO.getParentTaskId()!=null && !taskDTO.getParentTaskId().isEmpty())
	{
		parentCreateTaskVO=taskVORepository.findByTaskId(Long.parseLong(taskDTO.getParentTaskId()));
		patentTaskVO.setParentId(parentCreateTaskVO.getTaskId());
		 patentTaskVO.setParentTask(parentCreateTaskVO.getTask());
		patentTaskVO=parentTaskVORepository.save(patentTaskVO);		 
		 createTaskVO.setParentTaskVO(patentTaskVO);
	}
	
	if(taskDTO!=null && taskDTO.getProjectId()!=null && !taskDTO.getProjectId().isEmpty())
	{
		projectVO=projectVORepository.findByProjectId(Long.parseLong(taskDTO.getProjectId()));
		createTaskVO.setProjectVO(projectVO);
	}	
	
	TaskVO taskVO=taskVORepository.save(createTaskVO);
	if(taskVO!=null && taskVO.getTaskId()!=0)
		taskId=taskVO.getTaskId();
	return taskId;
	}

	private TaskVO mapTaskVo(TaskDTO taskDTO)
	{
		TaskVO taskVO=new TaskVO();
		System.out.println("******* TaskDTO object the  **********\n"+taskDTO.toString());
		
		taskVO.setEndDate(LocalDate.parse(taskDTO.getEndDate()));
		taskVO.setPriority(Integer.parseInt(taskDTO.getPriority()));
		taskVO.setStartDate(LocalDate.parse(taskDTO.getStartDate()));
		taskVO.setStatus(taskDTO.getStatus());
		taskVO.setTask(taskDTO.getTask());
		if(taskDTO.getTaskId()!=null && !taskDTO.getTaskId().isEmpty())
		 taskVO.setTaskId(Long.parseLong(taskDTO.getTaskId()));
		
		
		
		return taskVO;
	}
	
	private TaskDTO mapTaskDto(TaskVO taskVO)
	{
		TaskDTO taskDTO=new TaskDTO();
		System.out.println("******* taskVO object the  **********\n"+taskVO.toString());
		taskDTO.setTaskId(String.valueOf(taskVO.getTaskId()));
		taskDTO.setStartDate(String.valueOf(taskVO.getStartDate()));
		taskDTO.setEndDate(String.valueOf(taskVO.getEndDate()));
		taskDTO.setPriority(String.valueOf(taskVO.getPriority()));
		taskDTO.setStatus(String.valueOf(taskVO.getStatus()));
		taskDTO.setTask(taskVO.getTask());
		if(taskVO.getParentTaskVO()!=null && taskVO.getParentTaskVO().getParentId()>0L)
		taskDTO.setParentTaskId(String.valueOf(taskVO.getParentTaskVO().getParentId()));
		
		if(taskVO.getProjectVO()!=null && taskVO.getProjectVO().getProjectId()>0L)
		taskDTO.setProjectId(String.valueOf(taskVO.getProjectVO().getProjectId()));
		
		
		return taskDTO;
	}
}

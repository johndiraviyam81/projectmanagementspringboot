package com.projectmanagement.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.projectmanagement.app.entity.TaskVO;
import com.projectmanagement.app.entity.UsersVO;
import com.projectmanagement.app.entity.ParentTaskVO;
import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.repositories.TaskVORepository;
import com.projectmanagement.app.repositories.UsersVORepository;
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
	
	@Autowired
	UsersVORepository usersVORepository;
	
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
	public List<TaskDTO> searchTasks(String taskName) 
	{
		
		List<TaskDTO> allTasks=new ArrayList<TaskDTO>();
		
		List<TaskVO> taskList=new ArrayList<TaskVO>();
		try
		{
			taskList= taskVORepository.findByTaskContaining(taskName);
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
	public TaskDTO getTaskById(long taskId) 
	{
		
		TaskDTO taskDTO=new TaskDTO();
		TaskVO taskVO=new TaskVO();
		 
		try
		{
			taskVO= taskVORepository.findByTaskId(taskId);
		 if(taskVO!=null && taskVO.getTaskId()>0L)
		 taskDTO=mapTaskDto(taskVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
					
			return taskDTO;
	}

	
	@Override
	@Transactional
	public TaskDTO getTaskByParentId(long parentById) 
	{
		
		TaskDTO taskDTO=new TaskDTO();
		ParentTaskVO parentTaskVO=new ParentTaskVO();
		 
		try
		{
			parentTaskVO= parentTaskVORepository.findByParentId(parentById);
		 if(parentTaskVO!=null && parentTaskVO.getParentId()>0L)
		 {
		 taskDTO=mapTaskDto(parentTaskVO.getParentTask());
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
					
			return taskDTO;
	}

	
	@Override
	@Transactional
	public long save(TaskDTO taskDTO) throws Exception {
	
	long taskId=0L;
	TaskVO parentCreateTaskVO=null;
	ParentTaskVO  patentTaskVO=new ParentTaskVO();
	ParentTaskVO  patentExistTaskVO=new ParentTaskVO();
	ProjectVO projectVO=new ProjectVO();
	TaskVO createTaskVO=mapTaskVo(taskDTO);
	if(taskDTO!=null && taskDTO.getParentTaskId()!=null && !taskDTO.getParentTaskId().isEmpty())
	{
		System.out.println("\n******* taskDTO.getParentTaskId()::"+taskDTO.getParentTaskId());
		parentCreateTaskVO=taskVORepository.findByTaskId(Long.parseLong(taskDTO.getParentTaskId()));
		patentExistTaskVO=parentTaskVORepository.findByParentTask(parentCreateTaskVO);
		if(patentExistTaskVO!=null && patentExistTaskVO.getParentId()>0)
		{	patentTaskVO=patentExistTaskVO; }
		else
		{
		 patentTaskVO.setParentTask(parentCreateTaskVO);
		patentTaskVO=parentTaskVORepository.save(patentTaskVO);	
		}
		 createTaskVO.setParentTaskVO(patentTaskVO);
	}
	
	if(taskDTO!=null && taskDTO.getProjectId()!=null && !taskDTO.getProjectId().isEmpty())
	{
		projectVO=projectVORepository.findByProjectId(Long.parseLong(taskDTO.getProjectId()));
		createTaskVO.setProjectVO(projectVO);
	}	
	
	if(taskDTO!=null && taskDTO.getUserId()!=null && !taskDTO.getUserId().isEmpty())
	{
		UsersVO userVoDt=usersVORepository.findByUserId(Long.parseLong(taskDTO.getUserId()));
		createTaskVO.setUsersVO(userVoDt);
		
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
		taskVO.setTask(taskDTO.getTaskName());
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
		taskDTO.setTaskName(taskVO.getTask());
		if(taskVO.getParentTaskVO()!=null && taskVO.getParentTaskVO().getParentId()>0L)
		{
		taskDTO.setParentTaskId(String.valueOf(taskVO.getParentTaskVO().getParentId()));
		taskDTO.setParentTaskName(taskVO.getParentTaskVO().getParentTask().getTask());
		}
		
		if(taskVO.getProjectVO()!=null && taskVO.getProjectVO().getProjectId()>0L)
		{
		taskDTO.setProjectId(String.valueOf(taskVO.getProjectVO().getProjectId()));
		taskDTO.setProjectName(taskVO.getProjectVO().getProject());
		}
		
		if(taskVO.getUsersVO()!=null && taskVO.getUsersVO().getUserId()>0L)
		{
			taskDTO.setUserId(String.valueOf(taskVO.getUsersVO().getUserId()));
			taskDTO.setUserName(taskVO.getUsersVO().getFirstName());
		}
		
		return taskDTO;
	}
}

package com.projectmanagement.app.service;

import java.sql.Date;
import java.sql.SQLException;
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



/**
 * The Class TasksServiceImpl.
 */
@Service
public class TasksServiceImpl implements TasksService {

	/** The task VO repository. */
	@Autowired
	TaskVORepository taskVORepository;
	
	/** The parent task VO repository. */
	@Autowired
	ParentTaskVORepository parentTaskVORepository;
	
	/** The project VO repository. */
	@Autowired
	ProjectVORepository projectVORepository;
	
	/** The users VO repository. */
	@Autowired
	UsersVORepository usersVORepository;
	
	/* (non-Javadoc)
	 * @see com.projectmanagement.app.service.TasksService#getAllTasks()
	 */
	@Override
	@Transactional
	public List<TaskDTO> getAllTasks() throws Exception
	{
		
		List<TaskDTO> allTasks=new ArrayList<>();
		
		List<TaskVO> taskList= taskVORepository.findAll();
		 if(taskList!=null && !taskList.isEmpty())
		 {
			taskList.stream().forEach(taskVO->allTasks.add(mapTaskDto(taskVO)));
		 }
					
		return allTasks;
	}
	
	/* (non-Javadoc)
	 * @see com.projectmanagement.app.service.TasksService#searchTasks(java.lang.String)
	 */
	@Override
	@Transactional
	public List<TaskDTO> searchTasks(String taskName) 
	{
		
		List<TaskDTO> allTasks=new ArrayList<>();
		
		List<TaskVO> taskList= taskVORepository.findByTaskContaining(taskName);
			
			if(taskList!=null && !taskList.isEmpty())
			 {
				taskList.stream().forEach(taskVO->allTasks.add(mapTaskDto(taskVO)));
			 }
							
			return allTasks;
	}
	
	/* (non-Javadoc)
	 * @see com.projectmanagement.app.service.TasksService#getTaskById(long)
	 */
	@Override
	@Transactional
	public TaskDTO getTaskById(long taskId) throws Exception
	{
		
		TaskDTO taskDTO=new TaskDTO();
		TaskVO taskVO=taskVORepository.findByTaskId(taskId);
		 if(taskVO!=null && taskVO.getTaskId()>0L)
		 { taskDTO=mapTaskDto(taskVO); }
		
		return taskDTO;
	}

	/* (non-Javadoc)
	 * @see com.projectmanagement.app.service.TasksService#deleteByTaskById(long)
	 */
	@Override
	@Transactional
	public boolean deleteByTaskById(long taskId) throws Exception
	{
		boolean deleteFlag=false;		
		TaskVO deleteTaskVO=taskVORepository.findByTaskId(taskId);
		parentTaskVORepository.deleteByParentTask(deleteTaskVO);
		taskVORepository.deleteByTaskId(taskId);
		
		deleteFlag=true;
		
		return deleteFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.projectmanagement.app.service.TasksService#getTaskByParentId(long)
	 */
	@Override
	@Transactional
	public TaskDTO getTaskByParentId(long parentById) 
	{
		
		TaskDTO taskDTO=new TaskDTO();
		ParentTaskVO parentTaskVO= parentTaskVORepository.findByParentId(parentById);
		 if(parentTaskVO!=null && parentTaskVO.getParentId()>0L)
		 {
		 taskDTO=mapTaskDto(parentTaskVO.getParentTask());
		 }
					
			return taskDTO;
	}

	
	/* (non-Javadoc)
	 * @see com.projectmanagement.app.service.TasksService#save(com.projectmanagement.app.model.TaskDTO)
	 */
	@Override
	@Transactional
	public long save(TaskDTO taskDTO) throws Exception {
	
	long taskId=0L;
	TaskVO parentCreateTaskVO=null;
	ParentTaskVO  patentTaskVO=new ParentTaskVO();
	
	TaskVO createTaskVO=mapTaskVo(taskDTO);
	
	if(taskDTO!=null && taskDTO.getParentTaskId()!=null && !taskDTO.getParentTaskId().isEmpty())
	{
		System.out.println("\n******* taskDTO.getParentTaskId()::"+taskDTO.getParentTaskId());
		parentCreateTaskVO=taskVORepository.findByTaskId(Long.parseLong(taskDTO.getParentTaskId()));
		ParentTaskVO patentExistTaskVO=parentTaskVORepository.findByParentTask(parentCreateTaskVO);
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
		ProjectVO projectVO=projectVORepository.findByProjectId(Long.parseLong(taskDTO.getProjectId()));
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

	/**
	 * Map task vo.
	 *
	 * @param taskDTO the task DTO
	 * @return the task VO
	 */
	private TaskVO mapTaskVo(TaskDTO taskDTO)
	{
		TaskVO taskVO=new TaskVO();
		
		
		taskVO.setEndDate(LocalDate.parse(taskDTO.getEndDate()));
		taskVO.setPriority(Integer.parseInt(taskDTO.getPriority()));
		taskVO.setStartDate(LocalDate.parse(taskDTO.getStartDate()));
		taskVO.setStatus(taskDTO.getStatus());
		taskVO.setTask(taskDTO.getTaskName());
		if(taskDTO.getTaskId()!=null && !taskDTO.getTaskId().isEmpty())
		 taskVO.setTaskId(Long.parseLong(taskDTO.getTaskId()));
		
		
		
		return taskVO;
	}
	
	/**
	 * Map task dto.
	 *
	 * @param taskVO the task VO
	 * @return the task DTO
	 */
	private TaskDTO mapTaskDto(TaskVO taskVO)
	{
		TaskDTO taskDTO=new TaskDTO();
		
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

package com.projectmanagement.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.projectmanagement.app.entity.ParentTaskVO;
import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.entity.TaskVO;
import com.projectmanagement.app.entity.UsersVO;
import com.projectmanagement.app.repositories.ProjectVORepository;
import com.projectmanagement.app.repositories.TaskVORepository;
import com.projectmanagement.app.repositories.UsersVORepository;
import com.projectmanagement.app.model.TaskDTO;
import com.projectmanagement.app.model.UserDTO;






@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersVORepository usersVORepository;
	
	@Autowired
	ProjectVORepository projectVORepository;
	
	@Autowired
	TaskVORepository taskVORepository;
	
	@Override
	@Transactional
	public List<UserDTO> getAllUsers() 
	{
		
		List<UserDTO> allUsers=new ArrayList<UserDTO>();
		
		List<UsersVO> userList=new ArrayList<UsersVO>();
		try
		{
			userList= usersVORepository.findAll();
		 if(userList!=null && userList.size()>0)
		 {
			for(UsersVO usersVO : userList) 
			{
				System.out.print(usersVO.toString());
				allUsers.add(mapUserDto(usersVO));				
			}
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
					
			return allUsers;
	}
	
	
	@Override
	@Transactional
	public List<UserDTO> searchUsers(List<String> userNames) throws Exception
	{
		
		List<UserDTO> allUsers=new ArrayList<UserDTO>();
		
		List<UsersVO> userList=new ArrayList<UsersVO>();
		
			userList= usersVORepository.findByFirstNameIn(userNames);
		 if(userList!=null && userList.size()>0)
		 {
			for(UsersVO usersVO : userList) 
			{
				System.out.print(usersVO.toString());
				allUsers.add(mapUserDto(usersVO));				
			}
		 }
		 
					
					
			return allUsers;
	}
	
	@Override
	@Transactional
	public UserDTO getUser(String userId) throws Exception {
	
		UserDTO userDTO=new UserDTO();
	
 
	UsersVO createUserVO=new UsersVO();
	if(userId!=null && !userId.isEmpty())
	{
		createUserVO=usersVORepository.findByUserId(Long.parseLong(userId));
		userDTO=mapUserDto(createUserVO);
	}
	return userDTO;
	
	}
	
	@Override
	@Transactional
	public long save(UserDTO userDTO) throws Exception {
	
	long userId=0L;
	
	TaskVO  taskVO=new TaskVO();
	ProjectVO projectVO=new ProjectVO();
	UsersVO createUserVO=mapUserVo(userDTO);
	if(userDTO!=null && userDTO.getTaskId()!=null && !userDTO.getTaskId().isEmpty())
	{
		taskVO=taskVORepository.findByTaskId(Long.parseLong(userDTO.getTaskId()));
		createUserVO.setTaskVO(taskVO);
	}
	
	if(userDTO!=null && userDTO.getProjectId()!=null && !userDTO.getProjectId().isEmpty())
	{
		projectVO=projectVORepository.findByProjectId(Long.parseLong(userDTO.getProjectId()));
		createUserVO.setProjectVO(projectVO);
	}	
	
	UsersVO usersVO=usersVORepository.save(createUserVO);
	if(usersVO!=null && usersVO.getUserId()>0L)
		userId=usersVO.getUserId();
	return userId;
	}

	private UsersVO mapUserVo(UserDTO userDTO)
	{
		UsersVO usersVO=new UsersVO();
		System.out.println("******* userDTO object the  **********\n"+userDTO.toString());
		
		usersVO.setFirstName(userDTO.getFirstName());
		usersVO.setLastName(userDTO.getLastName());
		usersVO.setEmployeeId(Integer.parseInt(userDTO.getEmployeeId()));
		if(userDTO.getUserId()!=null && !userDTO.getUserId().isEmpty())
			usersVO.setUserId(Long.parseLong(userDTO.getUserId()));
		
		
		return usersVO;
	}
	
	private UserDTO mapUserDto(UsersVO usersVO)
	{
		UserDTO userDTO=new UserDTO();
		System.out.println("******* usersVO object the  **********\n"+usersVO.toString());
		
		userDTO.setUserId(String.valueOf(usersVO.getUserId()));
		userDTO.setFirstName(usersVO.getFirstName());
		userDTO.setLastName(usersVO.getLastName());
		userDTO.setEmployeeId(String.valueOf(usersVO.getEmployeeId()));
		
	
		
		if(usersVO.getTaskVO()!=null && usersVO.getTaskVO().getTaskId()>0L)
		{
			userDTO.setTaskId(String.valueOf(usersVO.getTaskVO().getTaskId()));
			userDTO.setTaskName(usersVO.getTaskVO().getTask());
		}
		
		if(usersVO.getProjectVO()!=null && usersVO.getProjectVO().getProjectId()>0L)
		{
			userDTO.setProjectId(String.valueOf(usersVO.getProjectVO().getProjectId()));
			userDTO.setProjectName(usersVO.getProjectVO().getProject());
		}
		
		
		return userDTO;
	}
}

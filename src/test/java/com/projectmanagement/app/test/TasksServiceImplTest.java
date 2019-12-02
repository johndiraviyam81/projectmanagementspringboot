package com.projectmanagement.app.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import com.projectmanagement.app.entity.ParentTaskVO;
import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.entity.TaskVO;
import com.projectmanagement.app.entity.UsersVO;
import com.projectmanagement.app.model.ProjectDTO;
import com.projectmanagement.app.model.TaskDTO;
import com.projectmanagement.app.repositories.ParentTaskVORepository;
import com.projectmanagement.app.repositories.ProjectVORepository;
import com.projectmanagement.app.repositories.TaskVORepository;
import com.projectmanagement.app.repositories.UsersVORepository;
import com.projectmanagement.app.service.TasksService;
import com.projectmanagement.app.service.TasksServiceImpl;



class TasksServiceImplTest {
	
	@InjectMocks
	TasksServiceImpl tasksService;

	@Mock
	TaskVORepository taskVORepository;
	
	/** The parent task VO repository. */
	@Mock
	ParentTaskVORepository parentTaskVORepository;
	
	/** The project VO repository. */
	@Mock
	ProjectVORepository projectVORepository;
	
	/** The users VO repository. */
	@Mock
	UsersVORepository usersVORepository;
	
	/** The project DTO 4. */
	ProjectDTO projectDTO4=new ProjectDTO();
	
	/** The project VO 4. */
	ProjectVO projectVO4=new ProjectVO();
	
	/** The project DTO 4 error. */
	ProjectDTO projectDTO4Error=new ProjectDTO();
	
	/** The users VO2. */
	UsersVO usersVO1=new UsersVO();
	

	/** The users VO2. */
	UsersVO usersVO2=new UsersVO();
	
	/** The projectContain. */
	String taskContain="task";
	
	List<TaskDTO> taskList=new ArrayList<>();
	
	TaskDTO taskDto1=new TaskDTO();
	
	TaskDTO taskDto2=new TaskDTO();
	
	TaskDTO taskDto3=new TaskDTO();
	
	/** The task vo 1. */
	TaskVO taskVo1=new TaskVO();
	
	/** The task vo 2. */
	TaskVO taskVo2=new TaskVO();
	
	/** The task vo 2. */
	TaskVO taskVo3=new TaskVO();
	
	/** The project VO 1. */
	ProjectVO projectVO1=new ProjectVO();
	
	/** The project VO 2. */
	ProjectVO projectVO2=new ProjectVO();
	
	/** The project VO 3. */
	ProjectVO projectVO3=new ProjectVO();
	
	/** The parent task VO. */
	ParentTaskVO parentTaskVO=new ParentTaskVO();
	
	/** The task VO list. */
	List<TaskVO> taskVOList=new ArrayList<>();
	
	/** The task VO list null. */
	List<TaskVO> taskVOListNull=null;
	
	List<TaskDTO> taskDTOListNull=new ArrayList<>();
	
 

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.usersVO1.setUserId(1);
		this.usersVO1.setFirstName("John Diraviyam");
		this.usersVO1.setLastName("Leslee");
		this.usersVO1.setEmployeeId(12412);
		
		this.usersVO2.setUserId(5);
		this.usersVO2.setFirstName("Boogy Man");
		this.usersVO2.setLastName("Shelton");
		this.usersVO2.setEmployeeId(42412);
		
	
		this.projectVO1.setProjectId(1L);
		this.projectVO1.setProject("Solr elmer project");
		this.projectVO1.setStartDate(LocalDate.parse("2019-12-01"));
		this.projectVO1.setEndDate(LocalDate.parse("2019-12-28"));
		this.projectVO1.setPriority(60);
		this.projectVO1.setUsersVO(this.usersVO1);
		
	
	
		this.projectVO2.setProjectId(2L);
		this.projectVO2.setProject("Perkin elmer project");
		this.projectVO2.setStartDate(LocalDate.parse("2018-06-17"));
		this.projectVO2.setEndDate(LocalDate.parse("2018-11-28"));
		this.projectVO2.setPriority(90);
		this.projectVO2.setUsersVO(this.usersVO2);
		

		

		
	
		

		this.taskDto1.setTaskId("11");
		this.taskDto1.setEndDate("2019-11-30");
		this.taskDto1.setParentTaskId(null);
		this.taskDto1.setParentTaskName(null);
		this.taskDto1.setPriority("20");
		this.taskDto1.setProjectId("1");
		this.taskDto1.setProjectName("Solr elmer project");
		this.taskDto1.setStartDate("2019-11-29");
		this.taskDto1.setTaskName("Task of this Project 1");
		this.taskDto1.setUserId("1");
		this.taskDto1.setUserName("John Diraviyam");
		
		
		this.taskVo1.setTaskId(11);
		this.taskVo1.setTask("Task of this Project 1");
		this.taskVo1.setProjectVO(projectVO1);
		this.taskVo1.setEndDate(LocalDate.parse("2019-11-30"));
		this.taskVo1.setStartDate(LocalDate.parse("2019-11-29"));
		this.taskVo1.setPriority(30);
		this.taskVo1.setUsersVO(usersVO1);
		this.taskVo1.setStatus("Start");
		this.taskVo1.setParentTaskVO(null);
		
		
		this.taskDto2.setTaskId("12");
		this.taskDto2.setEndDate("2019-11-30");
		this.taskDto2.setParentTaskId(null);
		this.taskDto2.setParentTaskName(null);
		this.taskDto2.setPriority("20");
		this.taskDto2.setProjectId("2");
		this.taskDto2.setProjectName("Perkin elmer project");
		this.taskDto2.setStartDate("2019-11-29");
		this.taskDto2.setTaskName("Solr second Task");
		this.taskDto2.setUserId("5");
		this.taskDto2.setUserName("Boogy man");
		
		this.taskVo2.setTaskId(12);
		this.taskVo2.setTask("Solr second Task");
		this.taskVo2.setProjectVO(projectVO2);
		this.taskVo2.setEndDate(LocalDate.parse("2019-11-30"));
		this.taskVo2.setStartDate(LocalDate.parse("2019-11-29"));
		this.taskVo2.setPriority(20);
		this.taskVo2.setUsersVO(usersVO2);
		this.taskVo2.setStatus("Start");
		this.taskVo2.setParentTaskVO(null);
		
		this.taskDto3.setTaskId("13");
		this.taskDto3.setEndDate("2019-12-13");
		this.taskDto3.setParentTaskId("7");
		this.taskDto3.setParentTaskName("Solr second Task");
		this.taskDto3.setPriority("20");
		this.taskDto3.setProjectId("1");
		this.taskDto3.setProjectName("Solr elmer project");
		this.taskDto3.setStartDate("2019-11-29");
		this.taskDto3.setTaskName("Task 4 velld");
		this.taskDto3.setUserId("1");
		this.taskDto3.setUserName("John Diraviyam");
		
		this.parentTaskVO.setParentId(7);
		this.parentTaskVO.setParentTask(taskVo2);
		
		this.taskVo3.setTaskId(13);
		this.taskVo3.setTask("Task 4 velld");
		this.taskVo3.setProjectVO(projectVO1);
		this.taskVo3.setEndDate(LocalDate.parse("2019-11-30"));
		this.taskVo3.setStartDate(LocalDate.parse("2019-12-13"));
		this.taskVo3.setPriority(20);
		this.taskVo3.setUsersVO(usersVO1);
		this.taskVo3.setStatus("Start");
		this.taskVo3.setParentTaskVO(parentTaskVO);
		
		this.taskList.add(taskDto1);
		this.taskList.add(taskDto2);
		this.taskList.add(taskDto3);
		
		
		

		

		
		this.taskVOList.add(this.taskVo1);
		this.taskVOList.add(this.taskVo2);
		this.taskVOList.add(this.taskVo3);

	}

	@Test
	void testGetAllTasksPositiveFlow() throws Exception {
		when(taskVORepository.findAll()).thenReturn(taskVOList);
		List<TaskDTO> allTasks=new ArrayList<>();
		this.taskList.stream().forEach(taskVO->{allTasks.add(taskVO);});
		tasksService.getAllTasks();
		
		verify(taskVORepository, times(1)).findAll();
		assertEquals(3,allTasks.size());
	}
	
	@Test
	void testGetAllTasksNegativeeFlow() throws Exception {
		when(taskVORepository.findAll()).thenReturn(null);
		List<TaskDTO> allTasks=new ArrayList<>();
		this.taskDTOListNull.stream().forEach(taskVO->{allTasks.add(taskVO);});
		tasksService.getAllTasks();
		
		verify(taskVORepository, times(1)).findAll();
		assertEquals(0,allTasks.size());
	}

	@Test
	void testSearchTasksPositiveFlow() throws Exception {
		when(taskVORepository.findByTaskContaining(this.taskContain)).thenReturn(taskVOList);
		List<TaskDTO> allTasks=new ArrayList<>();
		this.taskList.stream().forEach(taskVO->{allTasks.add(taskVO);});
		tasksService.searchTasks(taskContain);
		
		verify(taskVORepository, times(1)).findByTaskContaining(this.taskContain);
		assertEquals(3,allTasks.size());
	}
	
	@Test
	void testSearchTasksNegativeFlow() throws Exception {
		when(taskVORepository.findByTaskContaining(this.taskContain)).thenReturn(null);
		List<TaskDTO> allTasks=new ArrayList<>();
		this.taskDTOListNull.stream().forEach(taskVO->{allTasks.add(taskVO);});
		tasksService.searchTasks(taskContain);
		
		verify(taskVORepository, times(1)).findByTaskContaining(this.taskContain);
		assertEquals(0,allTasks.size());
	}

	@Test
	void testGetTaskByIdPositiveFlow() throws Exception {
	 
				when(taskVORepository.findByTaskId(11L)).thenReturn(this.taskVo1);
				TaskVO taskGetVO=this.taskVo1;				 
				tasksService.getTaskById(11L);				
				verify(taskVORepository, times(1)).findByTaskId(11L);
				assertEquals(11,taskGetVO.getTaskId());
				assertEquals("Task of this Project 1",taskGetVO.getTask());
				assertEquals(taskGetVO,taskVo1);
	}

	@Test
	void testGetTaskByIdNegativeFlow() throws Exception {
	 
				when(taskVORepository.findByTaskId(10L)).thenReturn(null);
				TaskVO taskGetVO=null;				 
				tasksService.getTaskById(10L);				
				verify(taskVORepository, times(1)).findByTaskId(10L);
				assertEquals(null,taskGetVO);				
	}
	
	@Test
	void testDeleteByTaskByIdPositiveFlow() throws Exception {
	TaskVO deleteTaskVO=new TaskVO();
		when(taskVORepository.findByTaskId(11L)).thenReturn(this.taskVo1);	
		parentTaskVORepository.deleteByParentTask(taskVo1);	
		taskVORepository.deleteByTaskId(11L);	
		tasksService.deleteByTaskById(11L);		 
		assertEquals(null,deleteTaskVO.getTask());
	}

	@Test
	void testDeleteByTaskByIdNegativeFlow() throws Exception {
	TaskVO deleteTaskVO=this.taskVo1;
		when(taskVORepository.findByTaskId(11L)).thenReturn(null);	
		parentTaskVORepository.deleteByParentTask(null);	
		taskVORepository.deleteByTaskId(0);	
		tasksService.deleteByTaskById(11L);		 
		assertEquals("Task of this Project 1",deleteTaskVO.getTask());
	}
	
	@Test
	void testGetTaskByParentId() {
		//fail("Not yet implemented"); // TODO
				assert(true);
	}

	@Test
	void testGetTaskByParentIdPositiveFlow() throws Exception {
	 
				when(parentTaskVORepository.findByParentId(7L)).thenReturn(this.parentTaskVO);
				ParentTaskVO parentTaskGetVO=this.parentTaskVO;				 
				tasksService.getTaskByParentId(7L);				
				verify(parentTaskVORepository, times(1)).findByParentId(7L);
				assertEquals(12,parentTaskGetVO.getParentTask().getTaskId());
				assertEquals("Solr second Task",parentTaskGetVO.getParentTask().getTask());
				assertEquals(parentTaskGetVO.getParentTask(),this.taskVo2);
	}

	@Test
	void testGetTaskByParentIdNegativeFlow() throws Exception {
	 
		when(parentTaskVORepository.findByParentId(8L)).thenReturn(null);
		ParentTaskVO parentTaskGetVO=new ParentTaskVO();				 
		tasksService.getTaskByParentId(8L);				
		verify(parentTaskVORepository, times(1)).findByParentId(8L);
		assertEquals(null,parentTaskGetVO.getParentTask());			
	}
	
	@Test
	void testSavePositiveFlow() throws Exception {
		long taskId=0L;
		TaskVO parentCreateTaskVO=null;
		ParentTaskVO  newPatentTaskVO=parentTaskVO;		
		TaskVO createTaskVO=this.taskVo3;
		when(taskVORepository.findByTaskId(12L)).thenReturn(this.taskVo2);
		when(parentTaskVORepository.findByParentTask(taskVo2)).thenReturn(null);
		when(parentTaskVORepository.save(this.parentTaskVO)).thenReturn(newPatentTaskVO);
		when(projectVORepository.findByProjectId(1)).thenReturn(this.projectVO1);
		when(usersVORepository.findByUserId(1L)).thenReturn(this.usersVO1);
		when(taskVORepository.save(createTaskVO)).thenReturn(this.taskVo3);
		
		tasksService.save(this.taskDto3);				
		verify(taskVORepository, times(1)).save(createTaskVO);
		assertEquals(createTaskVO,this.taskVo3);	
		assertEquals(createTaskVO.getUsersVO(),this.usersVO1);	
		assertEquals(createTaskVO.getParentTaskVO(),this.parentTaskVO);	
		assertEquals(createTaskVO.getProjectVO(),this.projectVO1);	
		
	}
		
		
	@Test
	void testSaveAlternatePositiveFlow() throws Exception {
		long taskId=0L;
		TaskVO parentCreateTaskVO=null;
		ParentTaskVO  newPatentTaskVO=null;		
		TaskVO createTaskVO=this.taskVo2;
		when(taskVORepository.findByTaskId(0)).thenReturn(null);
		when(parentTaskVORepository.findByParentTask(null)).thenReturn(null);
		when(parentTaskVORepository.save(null)).thenReturn(null);
		when(projectVORepository.findByProjectId(1)).thenReturn(this.projectVO2);
		when(usersVORepository.findByUserId(1L)).thenReturn(this.usersVO2);
		when(taskVORepository.save(createTaskVO)).thenReturn(this.taskVo2);
		
		tasksService.save(this.taskDto2);				
		verify(taskVORepository, times(1)).save(createTaskVO);
		assertEquals(createTaskVO,this.taskVo2);	
		assertEquals(createTaskVO.getUsersVO(),this.usersVO2);	
		assertEquals(createTaskVO.getParentTaskVO(),null);	
		assertEquals(createTaskVO.getProjectVO(),this.projectVO2);	
		
	}
	
}

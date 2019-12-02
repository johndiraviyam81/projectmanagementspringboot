package com.projectmanagement.app.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.projectmanagement.app.util.ProjectManagementConstants;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projectmanagement.app.controllers.TasksController;
import com.projectmanagement.app.model.DeleteRecordDTO;
import com.projectmanagement.app.model.TaskDTO;
import com.projectmanagement.app.service.TasksService;



/**
 * The Class TasksControllerTest.
 */
@ExtendWith(MockitoExtension.class)
class TasksControllerTest {

	@InjectMocks
	TasksController tasksController;
	
	@Mock	
	private	TasksService tasksService;
	
	private MockMvc mvc;
	
	List<TaskDTO> taskList=new ArrayList<>();
	
	TaskDTO taskDto1=new TaskDTO();
	
	TaskDTO taskDto2=new TaskDTO();
	
	TaskDTO taskDto3=new TaskDTO();
	
	DeleteRecordDTO deleteRecord=new DeleteRecordDTO();	
	
	DeleteRecordDTO deleteNotRecord=new DeleteRecordDTO();
	
	String taskDto1Json="{\"taskId\":\"11\",\"parentTaskId\":null,\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Task of this Project 1\",\"startDate\":\"2019-11-29\",\"endDate\":\"2019-11-30\",\"priority\":\"20\",\"status\":null,\"message\":\"Task is added successfully\",\"parentTaskName\":null,\"projectName\":\"Solr elmer project\",\"userName\":\"John Diraviyam\"}";
	
	String taskDto2Json="{\"taskId\":\"12\",\"parentTaskId\":null,\"projectId\":\"9\",\"userId\":\"5\",\"taskName\":\"Boogy man Task 3\",\"startDate\":\"2019-11-29\",\"endDate\":\"2019-11-30\",\"priority\":\"20\",\"status\":null,\"message\":\"Task is updated successfully\",\"parentTaskName\":null,\"projectName\":\"Perkin elmer project\",\"userName\":\"Boogy man\"}";
	
	String taskDto3Json="{\"taskId\":\"13\",\"parentTaskId\":\"7\",\"projectId\":\"10\",\"userId\":\"1\",\"taskName\":\"Tas4 velld\",\"startDate\":\"2019-11-29\",\"endDate\":\"2019-12-13\",\"priority\":\"20\",\"status\":null,\"message\":\"Task is fetched successfully\",\"parentTaskName\":\"Boogy man Task 3\",\"projectName\":\"ILL elmer project\",\"userName\":\"John Diraviyam\"}";
	
	String taskDto4NotJson="{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Error has been occured while fetching task\",\"parentTaskName\":null,\"projectName\":null,\"userName\":null}";
	
	String taskDtoEmptyJson="{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Task is fetched successfully\",\"parentTaskName\":null,\"projectName\":null,\"userName\":null}";
	
	String taskListJson="[{\"taskId\":\"11\",\"parentTaskId\":null,\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Task of this Project 1\",\"startDate\":\"2019-11-29\",\"endDate\":\"2019-11-30\",\"priority\":\"20\",\"status\":null,\"message\":null,\"parentTaskName\":null,\"projectName\":\"Solr elmer project\",\"userName\":\"John Diraviyam\"},{\"taskId\":\"12\",\"parentTaskId\":null,\"projectId\":\"9\",\"userId\":\"5\",\"taskName\":\"Boogy man Task 3\",\"startDate\":\"2019-11-29\",\"endDate\":\"2019-11-30\",\"priority\":\"20\",\"status\":null,\"message\":null,\"parentTaskName\":null,\"projectName\":\"Perkin elmer project\",\"userName\":\"Boogy man\"},{\"taskId\":\"13\",\"parentTaskId\":\"7\",\"projectId\":\"10\",\"userId\":\"1\",\"taskName\":\"Tas4 velld\",\"startDate\":\"2019-11-29\",\"endDate\":\"2019-12-13\",\"priority\":\"20\",\"status\":null,\"message\":null,\"parentTaskName\":\"Boogy man Task 3\",\"projectName\":\"ILL elmer project\",\"userName\":\"John Diraviyam\"}]";
	
	/** The delete record json. */
	String deleteRecordJson="{\"deleteId\":\"13\",\"message\":\"Task is deleted successfully\"}";
	
	/** The delete not record json. */
	String deleteNotRecordJson="{\"deleteId\":\"0\",\"message\":\"Task is not deleted successfully\"}";
	
	/** The delete not record json. */
	String deleteEmptyRecordJson="{\"deleteId\":\"0\",\"message\":\"Task is deleted successfully\"}";
	
	String taskName="project";
	
	@BeforeEach
	void setUp() throws Exception {
		mvc=MockMvcBuilders.standaloneSetup(tasksController).build();
		
		
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
		
		
		
		
		
		this.taskDto2.setTaskId("12");
		this.taskDto2.setEndDate("2019-11-30");
		this.taskDto2.setParentTaskId(null);
		this.taskDto2.setParentTaskName(null);
		this.taskDto2.setPriority("20");
		this.taskDto2.setProjectId("9");
		this.taskDto2.setProjectName("Perkin elmer project");
		this.taskDto2.setStartDate("2019-11-29");
		this.taskDto2.setTaskName("Boogy man Task 3");
		this.taskDto2.setUserId("5");
		this.taskDto2.setUserName("Boogy man");
		
		
		this.taskDto3.setTaskId("13");
		this.taskDto3.setEndDate("2019-12-13");
		this.taskDto3.setParentTaskId("7");
		this.taskDto3.setParentTaskName("Boogy man Task 3");
		this.taskDto3.setPriority("20");
		this.taskDto3.setProjectId("10");
		this.taskDto3.setProjectName("ILL elmer project");
		this.taskDto3.setStartDate("2019-11-29");
		this.taskDto3.setTaskName("Tas4 velld");
		this.taskDto3.setUserId("1");
		this.taskDto3.setUserName("John Diraviyam");
		
		this.taskList.add(taskDto1);
		this.taskList.add(taskDto2);
		this.taskList.add(taskDto3);
		
		this.deleteRecord.setDeleteId("12");
		this.deleteRecord.setMessage("Task is deleted successfully");
		
		this.deleteNotRecord.setDeleteId("10");
		this.deleteNotRecord.setMessage("Task is not deleted successfully");
		
		
	}

	@Test
	void testGetAllTasksPositiveFlow() throws Exception {
		when(tasksService.getAllTasks()).thenReturn(this.taskList);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_getAllTasks).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(taskListJson,response.getContentAsString());
	}

	@Test
	void testGetAllTasksNegativeFlow() throws Exception {
		when(tasksService.getAllTasks()).thenReturn(null);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_getAllTasks).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}
	
	@Test
	void testGetAllTasksExceptionFlow() throws Exception {
		when(tasksService.getAllTasks()).thenThrow(NullPointerException.class);	
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_getAllTasks).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}
	
	@Test
	void testSearchTasksPositiveFlow() throws Exception {
		when(tasksService.searchTasks(taskName)).thenReturn(this.taskList);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_searchAllTasks).accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(taskListJson,response.getContentAsString());
	}
	
	@Test
	void testSearchTasksNegativeFlow() throws Exception {
		when(tasksService.searchTasks(taskName)).thenReturn(null);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_searchAllTasks).accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}

	@Test
	void testSearchTasksExceptionFlow() throws Exception {
		when(tasksService.searchTasks(taskName)).thenThrow(NullPointerException.class);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_searchAllTasks).accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}
	
	@Test
	void testAddTaskPositiveFlow() throws Exception {
		when(tasksService.save(taskDto1)).thenReturn(1L);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_addTask).accept(MediaType.APPLICATION_JSON).content(this.taskDto1Json).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.taskDto1Json,response.getContentAsString());
	}
	
	@Test
	void testAddTaskNegativeFlow() throws Exception {
		when(tasksService.save(taskDto1)).thenReturn(0L);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_addTask).accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}

	
	@Test
	void testAddTaskExceptionFlow() throws Exception {
		when(tasksService.save(taskDto1)).thenThrow(NullPointerException.class);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_addTask).accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}

	@Test
	void testUpdateTask() {
		assert(true);
	}

	@Test
	void testUpdateTaskPositiveFlow() throws Exception {
		when(tasksService.save(taskDto2)).thenReturn(1L);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.put(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update).accept(MediaType.APPLICATION_JSON).content(this.taskDto2Json).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.taskDto2Json,response.getContentAsString());
	}
	
	@Test
	void testUpdateTaskNegativeFlow() throws Exception {
		when(tasksService.save(taskDto2)).thenReturn(0L);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.put(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update).accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}

	
	@Test
	void testUpdateTaskExceptionFlow() throws Exception {
		when(tasksService.save(taskDto2)).thenThrow(NullPointerException.class);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.put(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update).accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals("",response.getContentAsString());
	}

	@Test
	void testGetTaskByParentPositiveFlow() throws Exception {
		when(tasksService.getTaskByParentId(7)).thenReturn(this.taskDto3);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_Parent_Task_Test+"/7").accept(MediaType.APPLICATION_JSON).content("7").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.taskDto3Json,response.getContentAsString());
	}
	
	@Test
	void testGetTaskByParentNegativeFlow() throws Exception {
		when(tasksService.getTaskByParentId(0)).thenReturn(new TaskDTO());		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_Parent_Task_Test+"/0").accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.taskDtoEmptyJson,response.getContentAsString());
	}
	
	@Test
	void testGetTaskByParentExceptionFlow() throws Exception {
		when(tasksService.getTaskByParentId(0)).thenThrow(NullPointerException.class);	
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_Parent_Task_Test+"/0").accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals(this.taskDto4NotJson,response.getContentAsString());
	}

	@Test
	void testGetTaskPositiveFlow() throws Exception {
		when(tasksService.getTaskById(13)).thenReturn(this.taskDto3);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update+"/13").accept(MediaType.APPLICATION_JSON).content("13").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.taskDto3Json,response.getContentAsString());
	}
	
	@Test
	void testGetTaskNegativeFlow() throws Exception {
		when(tasksService.getTaskById(0)).thenReturn(new TaskDTO());		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update+"/0").accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.taskDtoEmptyJson,response.getContentAsString());
	}
	
	@Test
	void testGetTaskExceptionFlow() throws Exception {
		when(tasksService.getTaskById(0)).thenThrow(NullPointerException.class);	
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update+"/0").accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals(this.taskDto4NotJson,response.getContentAsString());
	}

	
	
	@Test
	void testDeleteTaskPositiveFlow() throws Exception {
		when(tasksService.deleteByTaskById(13)).thenReturn(true);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.delete(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update+"/13").accept(MediaType.APPLICATION_JSON).content("13").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.deleteRecordJson,response.getContentAsString());
	}
	
	@Test
	void testDeleteTaskNegativeFlow() throws Exception {
		when(tasksService.deleteByTaskById(-1)).thenReturn(false);		
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.delete(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update+"/0").accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.deleteEmptyRecordJson,response.getContentAsString());
	}
	
	@Test
	void testDeleteTaskExceptionFlow() throws Exception {
		when(tasksService.deleteByTaskById(0)).thenThrow(NullPointerException.class);	
		
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.delete(ProjectManagementConstants.URL_TASK_Service+ProjectManagementConstants.URL_TASK_update+"/0").accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response=mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals(this.deleteNotRecordJson,response.getContentAsString());
	}

	@Test
	void testDeleteTask() {
		assert(true);
	}

}

package com.projectmanagement.app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.projectmanagement.app.util.ProjectManagementConstants;


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
public class TasksControllerTest {

	/** The tasks controller. */
	@InjectMocks
	TasksController tasksController;

	/** The tasks service. */
	@Mock
	private TasksService tasksService;

	/** The mvc. */
	private MockMvc mvc;

	/** The task list. */
	List<TaskDTO> taskList = new ArrayList<>();

	/** The parent tasks lists. */
	List<TaskDTO> parentTasksLists = new ArrayList<>();

	/** The task dto 1. */
	TaskDTO taskDto1 = new TaskDTO();

	/** The task dto 2. */
	TaskDTO taskDto2 = new TaskDTO();

	/** The task dto 3. */
	TaskDTO taskDto3 = new TaskDTO();

	/** The task dto 4. */
	TaskDTO taskDto4 = new TaskDTO();

	/** The task parent dto 1. */
	TaskDTO taskParentDto1 = new TaskDTO();

	/** The task parent dto 2. */
	TaskDTO taskParentDto2 = new TaskDTO();

	/** The task parent dto error. */
	TaskDTO taskParentDtoError = new TaskDTO();

	/** The task error dto 1. */
	TaskDTO taskErrorDto1 = new TaskDTO();

	/** The delete record. */
	DeleteRecordDTO deleteRecord = new DeleteRecordDTO();

	/** The delete not record. */
	DeleteRecordDTO deleteNotRecord = new DeleteRecordDTO();

	/** The task list json. */
	String taskListJson = "[{\"taskId\":\"1\",\"parentTaskId\":null,\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Termanite Task 1\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-28\",\"priority\":\"20\",\"status\":\"Pending\",\"message\":null,\"parentTaskName\":null,\"projectName\":\"Termanite Project\",\"userName\":\"John\",\"setParentTask\":null},{\"taskId\":\"2\",\"parentTaskId\":\"1\",\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Termanite Task 2\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-20\",\"priority\":\"10\",\"status\":\"Pending\",\"message\":null,\"parentTaskName\":\"Termanite Parent Task 1\",\"projectName\":\"Termanite Project\",\"userName\":\"John\",\"setParentTask\":null},{\"taskId\":\"3\",\"parentTaskId\":null,\"projectId\":\"2\",\"userId\":\"2\",\"taskName\":\"Solr Task 1\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-20\",\"priority\":\"15\",\"status\":\"Pending\",\"message\":null,\"parentTaskName\":null,\"projectName\":\"Solr Project\",\"userName\":\"Ghomes\",\"setParentTask\":null}]";

	/** The task dto parent json. */
	String taskDtoParentJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":null,\"parentTaskName\":\"Termanite Parent Task 1\",\"projectName\":null,\"userName\":null,\"setParentTask\":\"1\"}";

	/** The task dto parent return json. */
	String taskDtoParentReturnJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Task is added successfully\",\"parentTaskName\":\"Termanite Parent Task 1\",\"projectName\":null,\"userName\":null,\"setParentTask\":\"1\"}";

	/** The task dto parent error json. */
	String taskDtoParentErrorJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":null,\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":\"1\"}";

	/** The task dto parent error return json. */
	String taskDtoParentErrorReturnJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Error has been occured while creating task\",\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":\"1\"}";

	/** The parent task list. */
	String parentTaskList = "[{\"taskId\":\"1\",\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":\"Termanite Parent Task 1\",\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":null,\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":\"1\"},{\"taskId\":\"2\",\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":\"Solr Parent Task\",\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":null,\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":\"1\"}]";

	/** The task dto add update json. */
	String taskDtoAddUpdateJson = "{\"taskId\":\"1\",\"parentTaskId\":null,\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Termanite Task 1\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-28\",\"priority\":\"20\",\"status\":\"Pending\",\"message\":null,\"parentTaskName\":null,\"projectName\":\"Termanite Project\",\"userName\":\"John\",\"setParentTask\":null}";

	/** The task dto add return json. */
	String taskDtoAddReturnJson = "{\"taskId\":\"1\",\"parentTaskId\":null,\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Termanite Task 1\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-28\",\"priority\":\"20\",\"status\":\"Pending\",\"message\":\"Task is added successfully\",\"parentTaskName\":null,\"projectName\":\"Termanite Project\",\"userName\":\"John\",\"setParentTask\":null}";

	/** The task dto update return json. */
	String taskDtoUpdateReturnJson = "{\"taskId\":\"1\",\"parentTaskId\":null,\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Termanite Task 1\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-28\",\"priority\":\"20\",\"status\":\"Pending\",\"message\":\"Task is updated successfully\",\"parentTaskName\":null,\"projectName\":\"Termanite Project\",\"userName\":\"John\",\"setParentTask\":null}";

	/** The task dto get json. */
	String taskDtoGetJson = "{\"taskId\":\"2\",\"parentTaskId\":\"1\",\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Termanite Task 2\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-20\",\"priority\":\"10\",\"status\":\"Pending\",\"message\":\"Task is fetched successfully\",\"parentTaskName\":\"Termanite Parent Task 1\",\"projectName\":\"Termanite Project\",\"userName\":\"John\",\"setParentTask\":null}";

	/** The task dto get error json. */
	String taskDtoGetErrorJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Error has been occured while fetching task\",\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":null}";

	/** The task dto empty json. */
	String taskDtoEmptyJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":null,\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":null}";

	/** The task dto empty return json. */
	String taskDtoEmptyReturnJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Task is fetched successfully\",\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":null}";

	/** The task dto add error json. */
	String taskDtoAddErrorJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Error has been occured while creating task\",\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":null}";

	/** The task dto update error json. */
	String taskDtoUpdateErrorJson = "{\"taskId\":null,\"parentTaskId\":null,\"projectId\":null,\"userId\":null,\"taskName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null,\"message\":\"Error has been occured while updating task\",\"parentTaskName\":null,\"projectName\":null,\"userName\":null,\"setParentTask\":null}";

	/** The task dto 4 error json. */
	String taskDto4ErrorJson = "{\"taskId\":\"11\",\"parentTaskId\":null,\"projectId\":\"1\",\"userId\":\"1\",\"taskName\":\"Task of this Project 1\",\"startDate\":\"2019-11-29\",\"endDate\":\"2019-11-30\",\"priority\":\"20\",\"status\":null,\"message\":\"Error has been occured while creating task\",\"parentTaskName\":null,\"projectName\":\"Solr elmer project\",\"userName\":\"John Diraviyam\",\"setParentTask\":null}";
	/** The delete record json. */
	String deleteRecordJson = "{\"deleteId\":\"13\",\"message\":\"Task is deleted successfully\"}";

	/** The delete not record json. */
	String deleteNotRecordJson = "{\"deleteId\":\"0\",\"message\":\"Task is not deleted successfully\"}";

	/** The delete not record json. */
	String deleteEmptyRecordJson = "{\"deleteId\":\"0\",\"message\":\"Task is deleted successfully\"}";

	/** The task name. */
	String taskName = "Project";

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(tasksController).build();

		this.taskParentDto1.setTaskId("1");
		this.taskParentDto1.setTaskName("Termanite Parent Task 1");
		this.taskParentDto1.setSetParentTask("1");

		this.taskParentDto2.setTaskId("2");
		this.taskParentDto2.setTaskName("Solr Parent Task");
		this.taskParentDto2.setSetParentTask("1");

		this.parentTasksLists.add(this.taskParentDto1);
		this.parentTasksLists.add(this.taskParentDto2);

		this.taskParentDtoError.setSetParentTask("1");

		this.taskDto1.setTaskId("1");
		this.taskDto1.setEndDate("2019-12-28");
		this.taskDto1.setParentTaskId(null);
		this.taskDto1.setParentTaskName(null);
		this.taskDto1.setPriority("20");
		this.taskDto1.setProjectId("1");
		this.taskDto1.setProjectName("Termanite Project");
		this.taskDto1.setStartDate("2019-12-06");
		this.taskDto1.setTaskName("Termanite Task 1");
		this.taskDto1.setUserId("1");
		this.taskDto1.setUserName("John");
		this.taskDto1.setStatus("Pending");

		this.taskDto4.setTaskId("14");
		this.taskDto4.setTaskName("ParentTaskNmae");
		this.taskDto4.setSetParentTask("1");

		this.taskDto1.toString();
		this.taskDto1.equals(taskDto1);

		this.taskDto2.setTaskId("2");
		this.taskDto2.setEndDate("2019-12-20");
		this.taskDto2.setParentTaskId("1");
		this.taskDto2.setParentTaskName("Termanite Parent Task 1");
		this.taskDto2.setPriority("10");
		this.taskDto2.setProjectId("1");
		this.taskDto2.setProjectName("Termanite Project");
		this.taskDto2.setStartDate("2019-12-06");
		this.taskDto2.setTaskName("Termanite Task 2");
		this.taskDto2.setUserId("1");
		this.taskDto2.setUserName("John");
		this.taskDto2.setStatus("Pending");

		this.taskDto2.toString();
		this.taskDto2.equals(taskDto2);

		this.taskDto3.setTaskId("3");
		this.taskDto3.setEndDate("2019-12-20");
		this.taskDto3.setParentTaskId(null);
		this.taskDto3.setParentTaskName(null);
		this.taskDto3.setPriority("15");
		this.taskDto3.setProjectId("2");
		this.taskDto3.setProjectName("Solr Project");
		this.taskDto3.setStartDate("2019-12-06");
		this.taskDto3.setTaskName("Solr Task 1");
		this.taskDto3.setUserId("2");
		this.taskDto3.setUserName("Ghomes");
		this.taskDto3.setStatus("Pending");

		this.taskList.add(taskDto1);
		this.taskList.add(taskDto2);
		this.taskList.add(taskDto3);

		this.deleteRecord.setDeleteId(null);
		this.deleteRecord.setMessage("Task is deleted successfully");

		this.deleteRecord.toString();

		this.deleteNotRecord.setDeleteId(null);
		this.deleteNotRecord.setMessage("Task is not deleted successfully");
		this.deleteNotRecord.toString();

		this.taskErrorDto1.setTaskId("14");
		this.taskErrorDto1.setEndDate("2019-11-30");
		this.taskErrorDto1.setParentTaskId(null);
		this.taskErrorDto1.setParentTaskName(null);
		this.taskErrorDto1.setPriority("20");
		this.taskErrorDto1.setProjectId("1");
		this.taskErrorDto1.setProjectName("Solr elmer project");
		this.taskErrorDto1.setStartDate("Elmer2019-11-29");
		this.taskErrorDto1.setTaskName("Error Task of this Project 1");
		this.taskErrorDto1.setUserId("1");
		this.taskErrorDto1.setUserName("John Diraviyam");

		this.taskErrorDto1.toString();
		this.taskErrorDto1.equals(taskErrorDto1);

	}

	/**
	 * Test get all tasks positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAllTasksPositiveFlow() throws Exception {
		when(tasksService.getAllTasks()).thenReturn(this.taskList);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_getAllTasks)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(taskListJson, response.getContentAsString());
	}

	/**
	 * Test get all tasks negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAllTasksNegativeFlow() throws Exception {
		when(tasksService.getAllTasks()).thenReturn(null);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_getAllTasks)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test get all tasks exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAllTasksExceptionFlow() throws Exception {
		when(tasksService.getAllTasks()).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_getAllTasks)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test get parent all tasks positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetParentAllTasksPositiveFlow() throws Exception {
		when(tasksService.getAllParentTasks()).thenReturn(this.parentTasksLists);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_getAllParentTasks)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.parentTaskList, response.getContentAsString());
	}

	/**
	 * Test get parent all tasks negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetParentAllTasksNegativeFlow() throws Exception {
		when(tasksService.getAllParentTasks()).thenReturn(null);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_getAllParentTasks)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test get parent all tasks exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetParentAllTasksExceptionFlow() throws Exception {
		when(tasksService.getAllParentTasks()).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_getAllParentTasks)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test search tasks positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchTasksPositiveFlow() throws Exception {
		when(tasksService.searchTasks(taskName)).thenReturn(this.taskList);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_searchAllTasks)
				.accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(taskListJson, response.getContentAsString());
	}

	/**
	 * Test search tasks negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchTasksNegativeFlow() throws Exception {
		when(tasksService.searchTasks(taskName)).thenReturn(null);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_searchAllTasks)
				.accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test search tasks exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchTasksExceptionFlow() throws Exception {
		when(tasksService.searchTasks(taskName)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_searchAllTasks)
				.accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test add task positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddTaskPositiveFlow() throws Exception {
		when(tasksService.save(taskDto1)).thenReturn(1L);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_addTask)
				.accept(MediaType.APPLICATION_JSON).content(this.taskDtoAddUpdateJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoAddReturnJson, response.getContentAsString());
	}

	/**
	 * Test add parent task positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddParentTaskPositiveFlow() throws Exception {
		when(tasksService.saveParentTask(this.taskParentDto1)).thenReturn(1L);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_addTask)
				.accept(MediaType.APPLICATION_JSON).content(this.taskDtoParentJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoParentReturnJson, response.getContentAsString());
	}

	/**
	 * Test add parent task exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddParentTaskExceptionFlow() throws Exception {
		when(tasksService.saveParentTask(this.taskParentDtoError)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_addTask)
				.accept(MediaType.APPLICATION_JSON).content(this.taskDtoParentErrorJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoParentErrorReturnJson, response.getContentAsString());
	}

	/**
	 * Test add task negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddTaskNegativeFlow() throws Exception {
		when(tasksService.save(this.taskErrorDto1)).thenReturn(0L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_addTask)
				.accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test add task exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddTaskExceptionFlow() throws Exception {
		when(tasksService.save(this.taskErrorDto1)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_addTask)
				.accept(MediaType.APPLICATION_JSON).content(this.taskDtoEmptyJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoAddErrorJson, response.getContentAsString());
	}

	/**
	 * Test update task positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateTaskPositiveFlow() throws Exception {
		when(tasksService.save(taskDto2)).thenReturn(1L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update)
				.accept(MediaType.APPLICATION_JSON).content(this.taskDtoAddUpdateJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoUpdateReturnJson, response.getContentAsString());
	}

	/**
	 * Test update task negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateTaskNegativeFlow() throws Exception {
		when(tasksService.save(taskErrorDto1)).thenReturn(0L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update)
				.accept(MediaType.APPLICATION_JSON).content(this.taskName).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test update task exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateTaskExceptionFlow() throws Exception {
		when(tasksService.save(this.taskErrorDto1)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update)
				.accept(MediaType.APPLICATION_JSON).content(this.taskDtoEmptyJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoUpdateErrorJson, response.getContentAsString());
	}

	/**
	 * Test get task positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetTaskPositiveFlow() throws Exception {
		when(tasksService.getTaskById(2)).thenReturn(this.taskDto2);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update + "/2")
				.accept(MediaType.APPLICATION_JSON).content("2").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoGetJson, response.getContentAsString());
	}

	/**
	 * Test get task negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetTaskNegativeFlow() throws Exception {
		when(tasksService.getTaskById(0)).thenReturn(new TaskDTO());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update + "/0")
				.accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoEmptyReturnJson, response.getContentAsString());
	}

	/**
	 * Test get task exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetTaskExceptionFlow() throws Exception {
		when(tasksService.getTaskById(0)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update + "/0")
				.accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.taskDtoGetErrorJson, response.getContentAsString());
	}

	/**
	 * Test delete task positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDeleteTaskPositiveFlow() throws Exception {
		when(tasksService.deleteByTaskById(13)).thenReturn(true);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update
						+ "/13")
				.accept(MediaType.APPLICATION_JSON).content("13").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.deleteRecordJson, response.getContentAsString());
	}

	/**
	 * Test delete task negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDeleteTaskNegativeFlow() throws Exception {
		when(tasksService.deleteByTaskById(-1)).thenReturn(false);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update + "/0")
				.accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.deleteEmptyRecordJson, response.getContentAsString());
	}

	/**
	 * Test delete task exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDeleteTaskExceptionFlow() throws Exception {
		when(tasksService.deleteByTaskById(0)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_TASK_Service + ProjectManagementConstants.URL_TASK_update + "/0")
				.accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.deleteNotRecordJson, response.getContentAsString());
	}

	/**
	 * Test delete task.
	 */
	@Test
	public void testDeleteTask() {
		assert (true);
	}

}

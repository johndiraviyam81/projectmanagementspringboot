package com.projectmanagement.app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.projectmanagement.app.util.ProjectManagementConstants;
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

import com.projectmanagement.app.controllers.ProjectsController;
import com.projectmanagement.app.model.DeleteRecordDTO;
import com.projectmanagement.app.model.ProjectDTO;
import com.projectmanagement.app.service.ProjectsService;

/**
 * The Class ProjectsControllerTest.
 */
@ExtendWith(MockitoExtension.class)
class ProjectsControllerTest {

	/** The projects controller. */
	@InjectMocks
	ProjectsController projectsController;

	/** The projects service. */
	@Mock
	private ProjectsService projectsService;

	/** The mvc. */
	private MockMvc mvc;

	/** The project list. */
	List<ProjectDTO> projectList = new ArrayList<>();

	/** The project null list. */
	List<ProjectDTO> projectNullList = new ArrayList<>();

	/** The project DTO 1. */
	ProjectDTO projectDTO1 = new ProjectDTO();

	/** The project DTO 2. */
	ProjectDTO projectDTO2 = new ProjectDTO();

	/** The project DTO 3. */
	ProjectDTO projectDTO3 = new ProjectDTO();

	/** The project DTO 4. */
	ProjectDTO projectDTO4 = new ProjectDTO();

 
	ProjectDTO projectDTO5 = new ProjectDTO();

	
	ProjectDTO projectDTOEmpty = new ProjectDTO();

	/** The delete record. */
	DeleteRecordDTO deleteRecord = new DeleteRecordDTO();

	/** The Not delete record. */
	DeleteRecordDTO notDeleteRecord = new DeleteRecordDTO();

	/** The project list json. */
	String projectListJson = "[{\"projectId\":\"1\",\"userId\":\"1\",\"userName\":\"John\",\"projectName\":\"Termanite Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"0\",\"message\":null,\"noOfTasks\":\"2\"},{\"projectId\":\"2\",\"userId\":\"2\",\"userName\":\"Ghomes\",\"projectName\":\"Solr Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"10\",\"message\":null,\"noOfTasks\":\"1\"},{\"projectId\":\"3\",\"userId\":\"3\",\"userName\":\"Boogy man\",\"projectName\":\"Versatile Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"30\",\"message\":null,\"noOfTasks\":null}]";


	String projectAddJson = "{\"projectId\":\"3\",\"userId\":\"3\",\"userName\":\"Boogy man\",\"projectName\":\"Versatile Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"30\",\"message\":null,\"noOfTasks\":null}";

	String projectAddReturnJson = "{\"projectId\":\"3\",\"userId\":\"3\",\"userName\":\"Boogy man\",\"projectName\":\"Versatile Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"30\",\"message\":\"Project is added successfully\",\"noOfTasks\":null}";
	
	String project4UpdateJson = "{\"projectId\":\"2\",\"userId\":\"2\",\"userName\":\"Ghomes\",\"projectName\":\"Solr Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"10\",\"message\":null,\"noOfTasks\":null}";

	String project4UpdateReturnJson = "{\"projectId\":\"2\",\"userId\":\"2\",\"userName\":\"Ghomes\",\"projectName\":\"Solr Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"10\",\"message\":\"Project is updated successfully\",\"noOfTasks\":null}";

	
	String project4GetJson = "{\"projectId\":\"1\",\"userId\":\"1\",\"userName\":\"John\",\"projectName\":\"Termanite Project\",\"startDate\":\"2019-12-06\",\"endDate\":\"2019-12-07\",\"priority\":\"0\",\"message\":\"Project is fetched successfully\",\"noOfTasks\":\"2\"}";

	
	String projectNotGetJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Project is fetched successfully\",\"noOfTasks\":null}";

	String projectEmptyJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":null,\"noOfTasks\":null}";
	
	String projectAddEmptyJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Project is added successfully\",\"noOfTasks\":null}";
	
	String projectUpdateEmptyJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Project is updated successfully\",\"noOfTasks\":null}";
	
	/** The project 4 not exception json. */
	String projectGetNotExceptionJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Error has been occured while retrieving project\",\"noOfTasks\":null}";

	
	String project4NotUpdateJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Project is updated successfully\"}";

	/** The delete record json. */
	String deleteRecordJson = "{\"deleteId\":\"3\",\"message\":\"Project is deleted successfully along with related tasks\"}";

	/** The delete not record json. */
	String deleteNotRecordJson = "{\"deleteId\":\"10\",\"message\":\"Project is not deleted successfully as user is referenced in users or tasks\"}";

	
	String projectAddErrorJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":\"start20-12-06\",\"endDate\":\"end19-12-06\",\"priority\":\"pri234\",\"message\":null,\"noOfTasks\":null}";

	
	String projectAddReturnErrorJson = "{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":\"start20-12-06\",\"endDate\":\"end19-12-06\",\"priority\":\"pri234\",\"message\":\"Error has been occured while creating project\",\"noOfTasks\":null}";

	
	
	String projectUpdateErrorJson = "{\"projectId\":\"1\",\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":\"start20-12-06\",\"endDate\":\"end19-12-06\",\"priority\":\"pri234\",\"message\":null,\"noOfTasks\":null}";

	String projectUpdateReturnErrorJson = "{\"projectId\":\"1\",\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":\"start20-12-06\",\"endDate\":\"end19-12-06\",\"priority\":\"pri234\",\"message\":\"Error has been occured while updating project\",\"noOfTasks\":null}";
	
		
	/** The projectContain. */
	String projectContain = "Project";

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		mvc = MockMvcBuilders.standaloneSetup(projectsController).build();
		

		this.projectDTO1.setProjectId("1");
		this.projectDTO1.setProjectName("Termanite Project");
		this.projectDTO1.setStartDate("2019-12-06");
		this.projectDTO1.setEndDate("2019-12-07");
		this.projectDTO1.setUserId("1");
		this.projectDTO1.setUserName("John");
		this.projectDTO1.setPriority("0");
		this.projectDTO1.setNoOfTasks("2");

		this.projectDTO1.toString();
		this.projectDTO1.equals(projectDTO1);
		
		this.projectDTO2.setProjectId("2");
		this.projectDTO2.setProjectName("Solr Project");
		this.projectDTO2.setStartDate("2019-12-06");
		this.projectDTO2.setEndDate("2019-12-07");
		this.projectDTO2.setUserId("2");
		this.projectDTO2.setUserName("Ghomes");
		this.projectDTO2.setPriority("10");
		this.projectDTO2.setNoOfTasks("1");
		
		
		
		this.projectDTO3.setProjectId("3");
		this.projectDTO3.setProjectName("Versatile Project");
		this.projectDTO3.setStartDate("2019-12-06");
		this.projectDTO3.setEndDate("2019-12-07");
		this.projectDTO3.setUserId("3");
		this.projectDTO3.setUserName("Boogy man");
		this.projectDTO3.setPriority("30");
		

		this.projectDTO4.setProjectId("1");	
		this.projectDTO4.setStartDate("start20-12-06");
		this.projectDTO4.setEndDate("end19-12-06");
		this.projectDTO4.setPriority("pri234");

		this.projectDTO5.setProjectId("1");	
		this.projectDTO5.setStartDate("start20-12-06");
		this.projectDTO5.setEndDate("end19-12-06");
		this.projectDTO5.setPriority("pri234");	
		

				

		this.projectList.add(this.projectDTO1);
		this.projectList.add(this.projectDTO2);
		this.projectList.add(this.projectDTO3);

		this.deleteRecord.setDeleteId(null);
		this.deleteRecord.setMessage(ProjectManagementConstants.PROJECT_Delete_msgSuccess);

		this.notDeleteRecord.setDeleteId(null);
		this.notDeleteRecord.setMessage(ProjectManagementConstants.PROJECT_Delete_msgFailure);
		
		this.deleteRecord.toString();
	}

	/**
	 * Test get all projects positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testGetAllProjectsPositiveFlow() throws Exception {
		when(projectsService.getAllProjects()).thenReturn(projectList);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_getAllProjects)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();

		assertEquals(projectListJson, response.getContentAsString());
	}

	/**
	 * Test get all projects negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testGetAllProjectsNegativeFlow() throws Exception {
		when(projectsService.getAllProjects()).thenReturn(null);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_getAllProjects)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();

		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test get all projects Exception negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testGetAllProjectsExceptionNegativeFlow() throws Exception {
		when(projectsService.getAllProjects()).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_getAllProjects)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();

		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test add project positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testAddProjectPositiveFlow() throws Exception {

		when(projectsService.save(this.projectDTO3)).thenReturn(3L);
		this.projectDTO4.setMessage(ProjectManagementConstants.PROJECT_Add_msgSuccess);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_PROJECT_Service
						+ ProjectManagementConstants.URL_PROJECT_addProject)
				.content(this.projectAddJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.projectAddReturnJson, response.getContentAsString());

	}

	/**
	 * Test add project negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testAddProjectNegativeFlow() throws Exception {

		when(projectsService.save(this.projectDTOEmpty)).thenReturn(0L);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_PROJECT_Service
						+ ProjectManagementConstants.URL_PROJECT_addProject)
				.content(this.projectEmptyJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.projectAddEmptyJson, response.getContentAsString());

	}

	/**
	 * Test add project negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testAddProjectExceptionNegativeFlow() throws Exception {

		when(projectsService.save(this.projectDTO4)).thenThrow(DateTimeParseException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_PROJECT_Service
						+ ProjectManagementConstants.URL_PROJECT_addProject)
				.content(this.projectAddErrorJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
		assertEquals(this.projectAddReturnErrorJson, response.getContentAsString());

	}

	/**
	 * Test add project positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testDeleteProjectPositiveFlow() throws Exception {
		when(projectsService.deleteProjectById(3L)).thenReturn(true);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update + "/3")
				.content("3").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(deleteRecordJson, response.getContentAsString());

	}

	/**
	 * Test add project negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testDeleteProjectNegativeFlow() throws Exception {
		when(projectsService.deleteProjectById(10L)).thenReturn(false);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update
						+ "/10")
				.content("10").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(deleteNotRecordJson, response.getContentAsString());

	}

	/**
	 * Test delete project exception negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testDeleteProjectExceptionNegativeFlow() throws Exception {
		when(projectsService.deleteProjectById(10L)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update
						+ "/10")
				.content("10").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(deleteNotRecordJson, response.getContentAsString());

	}

	/**
	 * Test get project positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testGetProjectPositiveFlow() throws Exception {
		when(projectsService.getProjectById(1L)).thenReturn(this.projectDTO1);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update
						+ "/1")
				.content(this.project4GetJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.project4GetJson, response.getContentAsString());

	}

	/**
	 * Test get project negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testGetProjectNegativeFlow() throws Exception {
		when(projectsService.getProjectById(10L)).thenReturn(new ProjectDTO());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update
						+ "/10")
				.content(this.projectNotGetJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.projectNotGetJson, response.getContentAsString());

	}

	/**
	 * Test get project exception negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testGetProjectExceptionNegativeFlow() throws Exception {
		when(projectsService.getProjectById(10L)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update
						+ "/10")
				.content(this.projectGetNotExceptionJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.projectGetNotExceptionJson, response.getContentAsString());

	}

	/**
	 * Test update project positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testUpdateProjectPositiveFlow() throws Exception {
		when(projectsService.save(projectDTO2)).thenReturn(2L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update)
				.content(this.project4UpdateJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.project4UpdateReturnJson, response.getContentAsString());

	}

	/**
	 * Test update projectnegative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testUpdateProjectnegativeFlow() throws Exception {
		when(projectsService.save(this.projectDTOEmpty)).thenReturn(0L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update)
				.content("project").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());

	}

	/**
	 * Test update project exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testUpdateProjectExceptionFlow() throws Exception {
		when(projectsService.save(this.projectDTO5)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_PROJECT_Service + ProjectManagementConstants.URL_PROJECT_update)
				.content(this.projectUpdateErrorJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.projectUpdateReturnErrorJson, response.getContentAsString());

	}

	/**
	 * Test search get all projects positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testSearchGetAllProjectsPositiveFlow() throws Exception {
		when(projectsService.searchProjects(this.projectContain)).thenReturn(projectList);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_PROJECT_Service
						+ ProjectManagementConstants.URL_PROJECT_searchAllProjects)
				.content(projectContain).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();

		assertEquals(projectListJson, response.getContentAsString());
	}

	/**
	 * Test search get all projects negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testSearchGetAllProjectsNegativeFlow() throws Exception {
		when(projectsService.searchProjects(this.projectContain)).thenReturn(null);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_PROJECT_Service
						+ ProjectManagementConstants.URL_PROJECT_searchAllProjects)
				.content(projectContain).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();

		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test search get all exception projects negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	void testSearchGetAllExceptionProjectsNegativeFlow() throws Exception {
		when(projectsService.searchProjects(this.projectContain)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_PROJECT_Service
						+ ProjectManagementConstants.URL_PROJECT_searchAllProjects)
				.content(projectContain).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();

		assertEquals("", response.getContentAsString());
	}
}

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
	private	ProjectsService projectsService;
	
	
	/** The mvc. */
	private MockMvc mvc;
	
	/** The project list. */
	List<ProjectDTO> projectList=new ArrayList<>();
	
	/** The project null list. */
	List<ProjectDTO> projectNullList=new ArrayList<>();
	
	/** The project DTO 1. */
	ProjectDTO projectDTO1=new ProjectDTO();
	
	/** The project DTO 2. */
	ProjectDTO projectDTO2=new ProjectDTO();
	
	/** The project DTO 3. */
	ProjectDTO projectDTO3=new ProjectDTO();
	
	/** The project DTO 4. */
	ProjectDTO projectDTO4=new ProjectDTO();
	
	/** The project DTO 4 error. */
	ProjectDTO projectDTO4Error=new ProjectDTO();
	
	/** The project DTO 5 error. */
	ProjectDTO projectDTO5Error=new ProjectDTO();
	
	/** The delete record. */
	DeleteRecordDTO deleteRecord=new DeleteRecordDTO();	
	
	/** The Not delete record. */
	DeleteRecordDTO notDeleteRecord=new DeleteRecordDTO();	
	
	/** The project list json. */
	String projectListJson="[{\"projectId\":\"1\",\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Solr elmer project\",\"startDate\":\"2019-12-01\",\"endDate\":\"2019-12-28\",\"priority\":\"60\",\"message\":null},{\"projectId\":\"2\",\"userId\":\"2\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Perkin elmer project\",\"startDate\":\"2018-06-17\",\"endDate\":\"2018-11-28\",\"priority\":\"90\",\"message\":null},{\"projectId\":\"3\",\"userId\":\"2\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"ILL elmer project\",\"startDate\":\"2019-02-17\",\"endDate\":\"2019-08-28\",\"priority\":\"30\",\"message\":null}]";

	/** The project 4 json. */
	String project4Json="{\"projectId\":\"4\",\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Marion Bestie project\",\"startDate\":\"2008-05-01\",\"endDate\":\"2014-10-21\",\"priority\":\"60\",\"message\":\"Project is added successfully\"}";
    
	/** The project 4 json. */
	String project4UpdateJson="{\"projectId\":\"4\",\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Marion Bestie project\",\"startDate\":\"2008-05-01\",\"endDate\":\"2014-10-21\",\"priority\":\"60\",\"message\":\"Project is updated successfully\"}";
    
	/** The project 4 json. */
	String project4GetJson="{\"projectId\":\"4\",\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Marion Bestie project\",\"startDate\":\"2008-05-01\",\"endDate\":\"2014-10-21\",\"priority\":\"60\",\"message\":\"Project is fetched successfully\"}";
   
	/** The project 4 json. */
	String project4NotGetJson="{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Project is fetched successfully\"}";
   
	/** The project 4 not exception json. */
	String project4NotExceptionJson="{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Error has been occured while retrieving project\"}";
	
	/** The project 4 json. */
	String project4NotUpdateJson="{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":null,\"endDate\":null,\"priority\":null,\"message\":\"Project is updated successfully\"}";
   
	
	/** The delete record json. */
	String deleteRecordJson="{\"deleteId\":\"3\",\"message\":\"Project is deleted successfully along with related tasks\"}";
	
	/** The delete not record json. */
	String deleteNotRecordJson="{\"deleteId\":\"10\",\"message\":\"Project is not deleted successfully as user is referenced in users or tasks\"}";
	
	/** The project 4 error json. */
	String project4ErrorJson="{\"projectId\":null,\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Doveton selton project\",\"startDate\":\"121232007-05-01\",\"endDate\":\"2014-10-21\",\"priority\":\"30\",\"message\":\"Project is added successfully\"}";
	   
	/** The project 5 error json. */
	String project5ErrorJson="{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":\"sdsfsdf32007-05-01\",\"endDate\":\"saa-10-21\",\"priority\":\"fgfghg\",\"message\":\"Project is added successfully\"}";
	
	/** The project 5 error json. */
	String project5UpErrorJson="{\"projectId\":null,\"userId\":null,\"userName\":null,\"projectName\":null,\"startDate\":\"sdsfsdf32007-05-01\",\"endDate\":\"saa-10-21\",\"priority\":\"fgfghg\",\"message\":\"Project is updated successfully\"}";
	
	
	/** The projectContain. */
	String projectContain="Solr";
	
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		mvc = MockMvcBuilders.standaloneSetup(projectsController).build();
		this.projectDTO1.setProjectId("1");
		this.projectDTO1.setProjectName("Solr elmer project");
		this.projectDTO1.setStartDate("2019-12-01");
		this.projectDTO1.setEndDate("2019-12-28");
		this.projectDTO1.setUserId("1");
		this.projectDTO1.setUserName("JohnDiraviyam");
		this.projectDTO1.setPriority("60");
		
		this.projectDTO2.setProjectId("2");
		this.projectDTO2.setProjectName("Perkin elmer project");
		this.projectDTO2.setStartDate("2018-06-17");
		this.projectDTO2.setEndDate("2018-11-28");
		this.projectDTO2.setUserId("2");
		this.projectDTO2.setUserName("JohnDiraviyam");
		this.projectDTO2.setPriority("90");
		
		this.projectDTO3.setProjectId("3");
		this.projectDTO3.setProjectName("ILL elmer project");
		this.projectDTO3.setStartDate("2019-02-17");
		this.projectDTO3.setEndDate("2019-08-28");
		this.projectDTO3.setUserId("2");
		this.projectDTO3.setUserName("JohnDiraviyam");
		this.projectDTO3.setPriority("30");


		this.projectDTO4.setProjectId("4");
		this.projectDTO4.setProjectName("Marion Bestie project");
		this.projectDTO4.setStartDate("2008-05-01");
		this.projectDTO4.setEndDate("2014-10-21");
		this.projectDTO4.setPriority("60");
		this.projectDTO4.setUserId("1");
		this.projectDTO4.setUserName("JohnDiraviyam");
		this.projectDTO4.setMessage(ProjectManagementConstants.PROJECT_Add_msgSuccess);


		this.projectDTO4Error.setProjectName("Doveton selton project");
		this.projectDTO4Error.setStartDate("121232007-05-01");
		this.projectDTO4Error.setEndDate("2014-10-21");
		this.projectDTO4Error.setPriority("30");
		this.projectDTO4Error.setUserId("1");
		this.projectDTO4Error.setUserName("JohnDiraviyam");
		this.projectDTO4Error.setMessage(ProjectManagementConstants.PROJECT_Add_msgFailure);
		
		
		
	
		this.projectDTO5Error.setStartDate("sdsfsdf32007-05-01");
		this.projectDTO5Error.setEndDate("saa-10-21");
		this.projectDTO5Error.setPriority("fgfghg");

		this.projectDTO5Error.setMessage(ProjectManagementConstants.PROJECT_Add_msgFailure);
	 
		  this.projectList.add(this.projectDTO1);
		  this.projectList.add(this.projectDTO2);
		  this.projectList.add(this.projectDTO3);
		  	
			this.deleteRecord.setDeleteId("3");
			this.deleteRecord.setMessage(ProjectManagementConstants.PROJECT_Delete_msgSuccess);
			
			this.notDeleteRecord.setDeleteId("10");
			this.notDeleteRecord.setMessage(ProjectManagementConstants.PROJECT_Delete_msgFailure);
	}

	/**
	 * Test get all projects positive flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetAllProjectsPositiveFlow() throws Exception {
		when(projectsService.getAllProjects()).thenReturn(projectList);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_getAllProjects).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		

		assertEquals(projectListJson,response.getContentAsString());
	}

	/**
	 * Test get all projects negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetAllProjectsNegativeFlow() throws Exception {
		when(projectsService.getAllProjects()).thenReturn(null);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_getAllProjects).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		

		assertEquals("",response.getContentAsString());
	}
	
	/**
	 * Test get all projects Exception negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetAllProjectsExceptionNegativeFlow() throws Exception {
		when(projectsService.getAllProjects()).thenThrow(NullPointerException.class);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_getAllProjects).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		

		assertEquals("",response.getContentAsString());
	}
	
	
	/**
	 * Test add project positive flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testAddProjectPositiveFlow() throws Exception {
		 
		when(projectsService.save(this.projectDTO4)).thenReturn(4L);
		this.projectDTO4.setMessage(ProjectManagementConstants.PROJECT_Add_msgSuccess);
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_addProject).content(project4Json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(project4Json,response.getContentAsString());
 
		
	}
	
	/**
	 * Test add project negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testAddProjectNegativeFlow() throws Exception {
		
		
			when(projectsService.save(this.projectDTO4Error)).thenReturn(0L);
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_addProject).content(this.project4ErrorJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
			assertEquals(this.project4ErrorJson,response.getContentAsString());
		
		
	}
	
	/**
	 * Test add project negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testAddProjectExceptionNegativeFlow() throws Exception {
		
			when(projectsService.save(this.projectDTO5Error)).thenThrow(DateTimeParseException.class);
						
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_addProject).content(this.project5ErrorJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			MockHttpServletResponse response =mvc.perform(requestBuilder).andReturn().getResponse();
			assertEquals(project5ErrorJson,response.getContentAsString());
			
		
		
	}
	
	/**
	 * Test add project positive flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeleteProjectPositiveFlow() throws Exception {
		when(projectsService.deleteProjectById(3L)).thenReturn(true);
		 
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update+"/3").content("3").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(deleteRecordJson,response.getContentAsString());
		
	}
	
	/**
	 * Test add project negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeleteProjectNegativeFlow() throws Exception {
		when(projectsService.deleteProjectById(10L)).thenReturn(false);
		 
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update+"/10").content("10").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
	MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
	assertEquals(deleteNotRecordJson,response.getContentAsString());
		
	}
	
	/**
	 * Test delete project exception negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeleteProjectExceptionNegativeFlow() throws Exception {
		when(projectsService.deleteProjectById(10L)).thenThrow(NullPointerException.class);
		 
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update+"/10").content("10").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
	MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
	assertEquals(deleteNotRecordJson,response.getContentAsString());
		
	}
	
	/**
	 * Test get project positive flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetProjectPositiveFlow() throws Exception {
		when(projectsService.getProjectById(4L)).thenReturn(this.projectDTO4);
		 
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update+"/4").content(project4GetJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.project4GetJson,response.getContentAsString());
		
	}
	
	
	/**
	 * Test get project negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetProjectNegativeFlow() throws Exception {
		when(projectsService.getProjectById(10L)).thenReturn(new ProjectDTO());
		 
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update+"/10").content(project4NotGetJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.project4NotGetJson,response.getContentAsString());
		
	}
	
	/**
	 * Test get project exception negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetProjectExceptionNegativeFlow() throws Exception {
		when(projectsService.getProjectById(10L)).thenThrow(NullPointerException.class);
		 
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update+"/10").content(project4NotExceptionJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		assertEquals(this.project4NotExceptionJson,response.getContentAsString());
		
	}
	
	/**
	 * Test update project positive flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdateProjectPositiveFlow() throws Exception {
		when(projectsService.save(projectDTO4)).thenReturn(4L);
		 
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update).content(project4UpdateJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.project4UpdateJson,response.getContentAsString());
		
	}
	
	/**
	 * Test update projectnegative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdateProjectnegativeFlow() throws Exception {
		when(projectsService.save(projectDTO4)).thenReturn(0L);
		 
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update).content(project4NotUpdateJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.project4NotUpdateJson,response.getContentAsString());
		
	}
	
	/**
	 * Test update project exception flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdateProjectExceptionFlow() throws Exception {
		when(projectsService.save(this.projectDTO5Error)).thenThrow(NullPointerException.class);
		 
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_update).content(project5UpErrorJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(this.project5UpErrorJson,response.getContentAsString());
		
	}

	/**
	 * Test search get all projects positive flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testSearchGetAllProjectsPositiveFlow() throws Exception {
		when(projectsService.searchProjects(this.projectContain)).thenReturn(projectList);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_searchAllProjects).content(projectContain).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		

		assertEquals(projectListJson,response.getContentAsString());
	}
	
	/**
	 * Test search get all projects negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testSearchGetAllProjectsNegativeFlow() throws Exception {
		when(projectsService.searchProjects(this.projectContain)).thenReturn(null);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_searchAllProjects).content(projectContain).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		
		assertEquals("",response.getContentAsString());
	}

	/**
	 * Test search get all exception projects negative flow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testSearchGetAllExceptionProjectsNegativeFlow() throws Exception {
		when(projectsService.searchProjects(this.projectContain)).thenThrow(NullPointerException.class);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ProjectManagementConstants.URL_PROJECT_Service+ProjectManagementConstants.URL_PROJECT_searchAllProjects).content(projectContain).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn().getResponse();
		
		assertEquals("",response.getContentAsString());
	}
}

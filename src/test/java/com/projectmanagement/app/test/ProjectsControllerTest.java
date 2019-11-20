package com.projectmanagement.app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.projectmanagement.app.controllers.ProjectsController;
import com.projectmanagement.app.model.ProjectDTO;
import com.projectmanagement.app.service.ProjectsService;

@ExtendWith(MockitoExtension.class)

class ProjectsControllerTest {
	
	@InjectMocks
	ProjectsController projectsController;
	
	@Mock
	private	ProjectsService projectsService;
	
	
	private MockMvc mvc;
	
	List<ProjectDTO> projectList=new ArrayList<ProjectDTO>();
	
	List<ProjectDTO> projectNullList=new ArrayList<ProjectDTO>();
	
	ProjectDTO projectDTO1=new ProjectDTO();
	
	ProjectDTO projectDTO2=new ProjectDTO();
	
	ProjectDTO projectDTO3=new ProjectDTO();
	
	ProjectDTO projectDTO4=new ProjectDTO();
	
	ProjectDTO projectDTO4Error=new ProjectDTO();
	
	String projectListJson="[{\"projectId\":\"1\",\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Solr elmer project\",\"startDate\":\"2019-12-01\",\"endDate\":\"2019-12-28\",\"priority\":\"60\",\"message\":null},{\"projectId\":\"2\",\"userId\":\"2\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Perkin elmer project\",\"startDate\":\"2018-06-17\",\"endDate\":\"2018-11-28\",\"priority\":\"90\",\"message\":null},{\"projectId\":\"3\",\"userId\":\"2\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"ILL elmer project\",\"startDate\":\"2019-02-17\",\"endDate\":\"2019-08-28\",\"priority\":\"30\",\"message\":null}]";

	String project4Json="{\"projectId\":null,\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Marion Bestie project\",\"startDate\":\"2008-05-01\",\"endDate\":\"2014-10-21\",\"priority\":\"60\",\"message\":\"Error has been occured while creating project\"}";
       
	
	String project4ErrorJson="{\"projectId\":null,\"userId\":\"1\",\"userName\":\"JohnDiraviyam\",\"projectName\":\"Doveton selton project\",\"startDate\":\"121232007-05-01\",\"endDate\":\"2014-10-21\",\"priority\":\"30\",\"message\":\"Error has been occured while creating project\"}";
	   
	ResponseEntity<List<ProjectDTO>> responseProject=null;
	
	String addSuccessMessage="Project is added successfully";
	
	String badRequestMessage="Error has been occured while creating project";
	
	
	
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


	
		this.projectDTO4.setProjectName("Marion Bestie project");
		this.projectDTO4.setStartDate("2008-05-01");
		this.projectDTO4.setEndDate("2014-10-21");
		this.projectDTO4.setPriority("60");
		this.projectDTO4.setUserId("2");
		this.projectDTO4.setUserName("JohnDiraviyam");
		this.projectDTO4.setMessage(addSuccessMessage);


		this.projectDTO4Error.setProjectName("Doveton selton project");
		this.projectDTO4Error.setStartDate("121232007-05-01");
		this.projectDTO4Error.setEndDate("2014-10-21");
		this.projectDTO4Error.setPriority("30");
		this.projectDTO4Error.setUserId("1");
		this.projectDTO4Error.setUserName("JohnDiraviyam");
		this.projectDTO4Error.setMessage(badRequestMessage);
	 
		  this.projectList.add(this.projectDTO1);
		  this.projectList.add(this.projectDTO2);
		  this.projectList.add(this.projectDTO3);
		 
	}

	@Test
	void testGetAllProjectsPositiveFlow() throws Exception {
		when(projectsService.getAllProjects()).thenReturn(projectList);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projects/allprojects").accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		

		assertEquals(projectListJson,response.getContentAsString());
	}

	@Test
	void testGetAllProjectsNegativeFlow() throws Exception {
		when(projectsService.getAllProjects()).thenReturn(null);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projects/allprojects").accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		

		assertEquals("",response.getContentAsString());
	}
	
	
	
	@Test
	void testAddProjectPositiveFlow() throws Exception {
		when(projectsService.save(this.projectDTO4)).thenReturn(9L);
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projects/add").content(project4Json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(project4Json,response.getContentAsString());
		
	}
	
	@Test
	void testAddProjectNegativeFlow() throws Exception {
		when(projectsService.save(this.projectDTO4Error)).thenReturn(0L);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projects/add").content(project4ErrorJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response =mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
		assertEquals(project4ErrorJson,response.getContentAsString());
		
	}

}

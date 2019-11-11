package com.projectmanagement.app.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.web.util.NestedServletException;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.model.ProjectDTO;
import com.projectmanagement.app.repositories.ProjectVORepository;
import com.projectmanagement.app.service.ProjectsService;
import com.projectmanagement.app.service.ProjectsServiceImpl;

class ProjectsServiceImplTest {

	@InjectMocks
	private	ProjectsServiceImpl projectsService;
	
	@Mock
	private	ProjectVORepository projectVORepository;
	
	
	
List<ProjectDTO> projectList=new ArrayList<ProjectDTO>();

List<ProjectVO> projectVOList=new ArrayList<ProjectVO>();

List<ProjectVO> projectVONullList=new ArrayList<ProjectVO>();
	
	List<ProjectDTO> projectNullList=new ArrayList<ProjectDTO>();
	
	ProjectDTO projectDTO1=new ProjectDTO();
	
	ProjectVO projectVO1=new ProjectVO();
	
	ProjectDTO projectDTO2=new ProjectDTO();
	
	ProjectVO projectVO2=new ProjectVO();
	
	ProjectDTO projectDTO3=new ProjectDTO();
	
	ProjectVO projectVO3=new ProjectVO();
	
	ProjectDTO projectDTO4=new ProjectDTO();
	
	ProjectVO projectVO4=new ProjectVO();
	
	ProjectDTO projectDTO4Error=new ProjectDTO();
	

	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.projectDTO1.setProjectId("1");
		this.projectDTO1.setProjectName("Solr elmer project");
		this.projectDTO1.setStartDate("2019-12-01");
		this.projectDTO1.setEndDate("2019-12-28");
		this.projectDTO1.setPriority("60");
		
		this.projectVO1.setProjectId(1L);
		this.projectVO1.setProject("Solr elmer project");
		this.projectVO1.setStartDate(LocalDate.parse("2019-12-01"));
		this.projectVO1.setEndDate(LocalDate.parse("2019-12-28"));
		this.projectVO1.setPriority(60);
		
		this.projectDTO2.setProjectId("2");
		this.projectDTO2.setProjectName("Perkin elmer project");
		this.projectDTO2.setStartDate("2018-06-17");
		this.projectDTO2.setEndDate("2018-11-28");
		this.projectDTO2.setPriority("90");
		
	
		this.projectVO2.setProjectId(2L);
		this.projectVO2.setProject("Perkin elmer project");
		this.projectVO2.setStartDate(LocalDate.parse("2018-06-17"));
		this.projectVO2.setEndDate(LocalDate.parse("2018-11-28"));
		this.projectVO2.setPriority(90);
		
		
		this.projectDTO3.setProjectId("3");
		this.projectDTO3.setProjectName("ILL elmer project");
		this.projectDTO3.setStartDate("2019-02-17");
		this.projectDTO3.setEndDate("2019-08-28");
		this.projectDTO3.setPriority("30");
		
		this.projectVO3.setProjectId(3L);
		this.projectVO3.setProject("ILL elmer project");
		this.projectVO3.setStartDate(LocalDate.parse("2019-02-17"));
		this.projectVO3.setEndDate(LocalDate.parse("2019-08-28"));
		this.projectVO3.setPriority(30);
		
		/*this.projectVO4.setProjectId(0L);
		this.projectVO4.setProject("ILL elmer project");
		
		this.projectVO4.setPriority(30);*/
				
		 this.projectList.add(this.projectDTO1);
		  this.projectList.add(this.projectDTO2);
		  this.projectList.add(this.projectDTO3);
		  
		  this.projectVOList.add(this.projectVO1);
		  this.projectVOList.add(this.projectVO2);
		  this.projectVOList.add(this.projectVO3);
	}

	@Test
	void testGetAllProjectsPossitiveFlow()  throws Exception {
		when(projectVORepository.findAll()).thenReturn(projectVOList);
		List<ProjectDTO> allProjects=new ArrayList<ProjectDTO>();
		
		allProjects=projectsService.getAllProjects();
		assertEquals(allProjects.size(),projectList.size());
	}
	 
	@Test
	void testGetAllProjectsNegativeFlow()  throws Exception {
		when(projectVORepository.findAll()).thenReturn(null);
		List<ProjectDTO> allProjects=new ArrayList<ProjectDTO>();
		
		allProjects=projectsService.getAllProjects();
		assertEquals(allProjects.size(),projectNullList.size());
	}
	
	@Test
	void testSavePostiveFlow() throws Exception {
		when(projectVORepository.save(this.projectVO3)).thenReturn(projectVO3);
			
		ProjectVO projectVOnew=projectVORepository.save(this.projectVO3);
		assertEquals(projectVOnew.getProjectId(),projectVO3.getProjectId());
	}
	
	@Test
	void testSaveNegativeFlow() throws Exception{
		when(projectVORepository.save(this.projectVO3)).thenReturn(null);
			
		ProjectVO projectVOnew=projectVORepository.save(this.projectVO3);
		assertEquals(projectVOnew,null);
	}

}

/**
 * 
 */
/**
 * @author 301153
 *
 */
package com.projectmanagement.app.load;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;

import org.jsmart.zerocode.jupiter.extension.ParallelLoadExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.projectmanagement.app.test.ProjectsControllerTest;

/**
 * The Class ProCntLoadMultiGrpAnnoteTest.
 */
@ExtendWith({ ParallelLoadExtension.class })
public class ProCntLoadMultiGrpAnnoteTest {

	/**
	 * Test projects controller load.
	 */
	@Test
	@LoadWith("load_generation.properties")
	@TestMappings({
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testGetAllProjectsPositiveFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testGetAllProjectsExceptionNegativeFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testAddProjectPositiveFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testAddProjectExceptionNegativeFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testDeleteProjectPositiveFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testDeleteProjectExceptionNegativeFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testGetProjectPositiveFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testGetProjectExceptionNegativeFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testUpdateProjectPositiveFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testUpdateProjectExceptionFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testSearchGetAllProjectsPositiveFlow"),
			@TestMapping(testClass = ProjectsControllerTest.class, testMethod = "testSearchGetAllExceptionProjectsNegativeFlow") })
	public void testProjectsControllerLoad() {
		// This space remains empty
	}

}
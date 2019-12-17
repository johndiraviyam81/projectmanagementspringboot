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
import com.projectmanagement.app.test.ProjectsServiceImplTest;

/**
 * The Class ProServiceLoadMultiGrpAnnoteTest.
 */
@ExtendWith({ ParallelLoadExtension.class })
public class ProServiceLoadMultiGrpAnnoteTest {

	/**
	 * Test projects service load.
	 */
	@Test
	@LoadWith("load_generation.properties")
	@TestMappings({
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testGetAllProjectsPositiveFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testSearchProjectsPositiveFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testGetAllProjectsNegativeFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testSearchProjectsNegativeFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testSavePostiveFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testSaveNegativeFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testGetProjectByIdPositiveFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testGetProjectByIdNegativeFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testDeleteProjectByIdPositiveFlow"),
			@TestMapping(testClass = ProjectsServiceImplTest.class, testMethod = "testDeleteProjectByIdNegativeFlow") })
	public void testProjectsServiceLoad() {
		// This space remains empty
	}

}
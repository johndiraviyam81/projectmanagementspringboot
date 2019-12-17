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

import com.projectmanagement.app.test.TasksControllerTest;

/**
 * The Class TskCntLoadMultiGrpAnnoteTest.
 */
@ExtendWith({ ParallelLoadExtension.class })
public class TskCntLoadMultiGrpAnnoteTest {

	/**
	 * Test tasks controller load.
	 */
	@Test
	@LoadWith("load_generation.properties")
	@TestMappings({ @TestMapping(testClass = TasksControllerTest.class, testMethod = "testGetAllTasksPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testGetAllTasksExceptionFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testGetParentAllTasksPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testGetParentAllTasksExceptionFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testSearchTasksPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testSearchTasksExceptionFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testAddTaskPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testAddParentTaskPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testAddParentTaskExceptionFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testAddTaskExceptionFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testUpdateTaskPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testUpdateTaskExceptionFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testGetTaskPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testGetTaskExceptionFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testDeleteTaskPositiveFlow"),
			@TestMapping(testClass = TasksControllerTest.class, testMethod = "testDeleteTaskExceptionFlow") })
	public void testTasksControllerLoad() {
		// This space remains empty
	}

}
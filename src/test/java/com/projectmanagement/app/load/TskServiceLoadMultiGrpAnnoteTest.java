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
import com.projectmanagement.app.test.TasksServiceImplTest;





@ExtendWith({ParallelLoadExtension.class})
public class TskServiceLoadMultiGrpAnnoteTest {

    @Test
    @LoadWith("load_generation.properties")
    @TestMappings({
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testGetAllTasksPositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testGetAllTasksNegativeeFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testSearchTasksPositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testSearchTasksNegativeFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testGetTaskByIdPositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testGetTaskByIdNegativeFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testDeleteByTaskByIdPositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testDeleteByTaskByIdNegativeFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testSavePositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testSaveAlternatePositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testSaveParentTaskPositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testGetAllParentTasksPositiveFlow"),
        @TestMapping(testClass = TasksServiceImplTest.class, testMethod = "testGetAllParentTasksNegativeeFlow")  
    })
    public void testTasksServiceLoad() {
        // This space remains empty
    }

}
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

import com.projectmanagement.app.test.UsersControllerTest;





@ExtendWith({ParallelLoadExtension.class})
public class UsrCntLoadMultiGrpAnnoteTest {

    @Test
    @LoadWith("load_generation.properties")
    @TestMappings({
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testGetAllUsersPositiveFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testGetAllUsersExceptionFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testSearchUsersPositiveFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testSearchUsersExceptionFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testUpdateUserPositiveFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testUpdateUserExceptionFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testAddUserPositiveFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testAddUserExceptionFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testGetUserPositiveFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testGetUserExceptionFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testDeleteUserPositiveFlow"),
        @TestMapping(testClass = UsersControllerTest.class, testMethod = "testDeleteUserExceptionFlow")
    })
    public void testUsersControllerLoad() {
        // This space remains empty
    }

}
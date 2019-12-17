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
import com.projectmanagement.app.test.UsersServiceImplTest;





@ExtendWith({ParallelLoadExtension.class})
public class UsrServiceLoadMultiGrpAnnoteTest {

    @Test
    @LoadWith("load_generation.properties")
    @TestMappings({
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testGetAllUsersPositiveFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testGetAllUsersNegativeFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testSearchUsersPositiveFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testSearchUsersNegativeFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testGetUserPositiveFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testGetUserNegativeFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testDeleteUserPositiveFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testDeleteUserAlternatePositiveFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testSavePositiveFlow"),
        @TestMapping(testClass = UsersServiceImplTest.class, testMethod = "testSaveNegativeFlow")
    })
    public void testUsersServiceLoad() {
        // This space remains empty
    }

}
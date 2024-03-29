package com.projectmanagement.app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.projectmanagement.app.util.ProjectManagementConstants;


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

import com.projectmanagement.app.controllers.UsersController;
import com.projectmanagement.app.model.DeleteRecordDTO;
import com.projectmanagement.app.model.UserDTO;
import com.projectmanagement.app.service.UsersService;

/**
 * The Class UsersControllerTest.
 */
@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

	/** The users controller. */
	@InjectMocks
	UsersController usersController;

	/** The users service. */
	@Mock
	private UsersService usersService;

	/** The mvc. */
	private MockMvc mvc;

	/** The user list. */
	List<UserDTO> userList = new ArrayList<>();

	/** The user list empty. */
	List<UserDTO> userListEmpty = new ArrayList<>();

	/** The user dto 1. */
	UserDTO userDto1 = new UserDTO();

	/** The user dto 2. */
	UserDTO userDto2 = new UserDTO();

	/** The user dto 3. */
	UserDTO userDto3 = new UserDTO();

	/** The delete record. */
	DeleteRecordDTO deleteRecord = new DeleteRecordDTO();

	/** The delete not record. */
	DeleteRecordDTO deleteNotRecord = new DeleteRecordDTO();

	/** The user dto 4 error. */
	UserDTO userDto4Error = new UserDTO();

	/** The user dto 5 error. */
	UserDTO userDto5Error = new UserDTO();

	/** The user dto 1 json. */
	String userDto1Json = "{\"userId\":\"1\",\"firstName\":\"John Diraviyam\",\"lastName\":\"Leslee\",\"employeeId\":\"13123\",\"message\":\"User is added successfully\"}";

	/** The user dto 2 json. */
	String userDto2Json = "{\"userId\":\"5\",\"firstName\":\"Boogy man\",\"lastName\":\"Shelton\",\"employeeId\":\"234324\",\"message\":\"User is updated successfully\"}";

	/** The user dto 3 json. */
	String userDto3Json = "{\"userId\":\"6\",\"firstName\":\"Viceroi\",\"lastName\":\"Gomez\",\"employeeId\":\"454234\",\"message\":\"User is received successfully\"}";

	/** The user dto 4 create error json. */
	String userDto4CreateErrorJson = "{\"userId\":\"0\"}";

	/** The user dto 4 error json. */
	String userDto4ErrorJson = "{\"userId\":null,\"firstName\":null,\"lastName\":null,\"employeeId\":\"abcde1234\",\"message\":null}";

	/** The user dto 4 return error json. */
	String userDto4ReturnErrorJson = "{\"userId\":null,\"firstName\":null,\"lastName\":null,\"employeeId\":\"abcde1234\",\"message\":\"Error has been occured while creating user\"}";

	/** The user dto 5 error json. */
	String userDto5ErrorJson = "{\"userId\":\"15\",\"firstName\":null,\"lastName\":null,\"employeeId\":\"abcde1234\",\"message\":null}";

	/** The user dto 5 return error json. */
	String userDto5ReturnErrorJson = "{\"userId\":\"15\",\"firstName\":null,\"lastName\":null,\"employeeId\":\"abcde1234\",\"message\":\"Error has been occured while updating user\"}";

	/** The user dto 6 empty json. */
	String userDto6EmptyJson = "{\"userId\":null,\"firstName\":null,\"lastName\":null,\"employeeId\":null,\"message\":\"User is received successfully\"}";

	/** The user dto 6 error json. */
	String userDto6ErrorJson = "{\"userId\":null,\"firstName\":null,\"lastName\":null,\"employeeId\":null,\"message\":\"Error has been occured while fetching user\"}";

	/** The user list json. */
	String userListJson = "[{\"userId\":\"1\",\"firstName\":\"John Diraviyam\",\"lastName\":\"Leslee\",\"employeeId\":\"13123\",\"message\":null},{\"userId\":\"5\",\"firstName\":\"Boogy man\",\"lastName\":\"Shelton\",\"employeeId\":\"234324\",\"message\":null},{\"userId\":\"6\",\"firstName\":\"Viceroi\",\"lastName\":\"Gomez\",\"employeeId\":\"454234\",\"message\":null}]";

	/** The delete record json. */
	String deleteRecordJson = "{\"deleteId\":null,\"message\":\"User is deleted successfully\"}";

	/** The delete not record json. */
	String deleteNotRecordJson = "{\"deleteId\":null,\"message\":\"User is not deleted successfully as user is referenced in projects or tasks\"}";

	/** The user list json empty. */
	String userListJsonEmpty = "";

	/** The user search strings json. */
	String userSearchStringsJson = "[\"John Diraviyam\",\"Boogy man\",\"Viceroi\"]";

	/** The user string. */
	List<String> userString = new ArrayList<>();

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(usersController).build();

		this.userDto1.setUserId("1");
		this.userDto1.setFirstName("John Diraviyam");
		this.userDto1.setLastName("Leslee");
		this.userDto1.setEmployeeId("13123");

		this.userDto1.toString();
		this.userDto1.equals(userDto1);

		this.userDto2.setUserId("5");
		this.userDto2.setFirstName("Boogy man");
		this.userDto2.setLastName("Shelton");
		this.userDto2.setEmployeeId("234324");

		this.userDto3.setUserId("6");
		this.userDto3.setFirstName("Viceroi");
		this.userDto3.setLastName("Gomez");
		this.userDto3.setEmployeeId("454234");

		this.userDto4Error.setUserId("0");
		this.userDto4Error.setEmployeeId("abcde1234");

		this.userDto5Error.setUserId("15");
		this.userDto5Error.setEmployeeId("abcde1234");

		System.out.println(this.userDto4Error.toString());

		this.deleteRecord.setDeleteId(null);
		this.deleteRecord.setMessage("User is deleted successfully");

		this.deleteNotRecord.setDeleteId(null);
		this.deleteNotRecord.setMessage("User is not deleted successfully as user is referenced in projects or tasks");

		this.userList.add(this.userDto1);
		this.userList.add(this.userDto2);
		this.userList.add(this.userDto3);

		this.userString.add("John Diraviyam");
		this.userString.add("Boogy man");
		this.userString.add("Viceroi");

	}

	/**
	 * Test get all users positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAllUsersPositiveFlow() throws Exception {
		when(usersService.getAllUsers()).thenReturn(this.userList);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_getAllUsers)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(userListJson, response.getContentAsString());
	}

	/**
	 * Test get all users negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAllUsersNegativeFlow() throws Exception {
		when(usersService.getAllUsers()).thenReturn(null);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_getAllUsers)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test get all users exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAllUsersExceptionFlow() throws Exception {
		when(usersService.getAllUsers()).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_getAllUsers)
				.accept(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test search users positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchUsersPositiveFlow() throws Exception {
		when(usersService.searchUsers(this.userString)).thenReturn(this.userList);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_searchAllUsers)
				.accept(MediaType.APPLICATION_JSON).content(this.userSearchStringsJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(this.userListJson, response.getContentAsString());

	}

	/**
	 * Test search users negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchUsersNegativeFlow() throws Exception {
		when(usersService.searchUsers(this.userString)).thenReturn(null);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_searchAllUsers)
				.accept(MediaType.APPLICATION_JSON).content(this.userSearchStringsJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());

	}

	/**
	 * Test search users exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchUsersExceptionFlow() throws Exception {
		when(usersService.searchUsers(this.userString)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_searchAllUsers)
				.accept(MediaType.APPLICATION_JSON).content(this.userSearchStringsJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());

	}

	/**
	 * Test update user positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateUserPositiveFlow() throws Exception {
		when(usersService.save(this.userDto2)).thenReturn(5L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update)
				.accept(MediaType.APPLICATION_JSON).content(this.userDto2Json).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(userDto2Json, response.getContentAsString());
	}

	/**
	 * Test update user negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateUserNegativeFlow() throws Exception {
		when(usersService.save(null)).thenReturn(0L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update)
				.accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test update user exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateUserExceptionFlow() throws Exception {
		when(usersService.save(this.userDto5Error)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update)
				.accept(MediaType.APPLICATION_JSON).content(this.userDto5ErrorJson)
				.contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(this.userDto5ReturnErrorJson, response.getContentAsString());
	}

	/**
	 * Test add user positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddUserPositiveFlow() throws Exception {
		when(usersService.save(this.userDto1)).thenReturn(1L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_addUser)
				.accept(MediaType.APPLICATION_JSON).content(this.userDto1Json).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(userDto1Json, response.getContentAsString());
	}

	/**
	 * Test add user negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddUserNegativeFlow() throws Exception {
		when(usersService.save(this.userDto4Error)).thenReturn(0L);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_addUser)
				.accept(MediaType.APPLICATION_JSON).content("").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals("", response.getContentAsString());
	}

	/**
	 * Test add user exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testAddUserExceptionFlow() throws Exception {
		when(usersService.save(this.userDto4Error)).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_addUser)
				.accept(MediaType.APPLICATION_JSON).content(userDto4ErrorJson).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(userDto4ReturnErrorJson, response.getContentAsString());
	}

	/**
	 * Test get user positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetUserPositiveFlow() throws Exception {
		when(usersService.getUser("6")).thenReturn(this.userDto3);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update + "/6")
				.accept(MediaType.APPLICATION_JSON).content("6").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(userDto3Json, response.getContentAsString());
	}

	/**
	 * Test get user negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetUserNegativeFlow() throws Exception {
		when(usersService.getUser("10")).thenReturn(new UserDTO());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update + "/10")
				.accept(MediaType.APPLICATION_JSON).content("10").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(userDto6EmptyJson, response.getContentAsString());
	}

	/**
	 * Test get user exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetUserExceptionFlow() throws Exception {
		when(usersService.getUser("0")).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update + "/0")
				.accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(userDto6ErrorJson, response.getContentAsString());
	}

	/**
	 * Test delete user positive flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDeleteUserPositiveFlow() throws Exception {
		when(usersService.deleteUser("6")).thenReturn(true);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update + "/6")
				.accept(MediaType.APPLICATION_JSON).content("6").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(deleteRecordJson, response.getContentAsString());
	}

	/**
	 * Test delete user negative flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDeleteUserNegativeFlow() throws Exception {
		when(usersService.deleteUser("10")).thenReturn(false);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update
						+ "/10")
				.accept(MediaType.APPLICATION_JSON).content("10").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn()
				.getResponse();
		assertEquals(deleteNotRecordJson, response.getContentAsString());
	}

	/**
	 * Test delete user exception flow.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDeleteUserExceptionFlow() throws Exception {
		when(usersService.deleteUser("0")).thenThrow(NullPointerException.class);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(ProjectManagementConstants.URL_USER_Service + ProjectManagementConstants.URL_USER_update + "/0")
				.accept(MediaType.APPLICATION_JSON).content("0").contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn()
				.getResponse();
		assertEquals(deleteNotRecordJson, response.getContentAsString());
	}

}

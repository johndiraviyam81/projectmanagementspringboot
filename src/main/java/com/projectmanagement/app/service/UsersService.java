package com.projectmanagement.app.service;

import java.util.List;

import com.projectmanagement.app.entity.UsersVO;
import com.projectmanagement.app.model.UserDTO;





public interface UsersService 
{
	public long save(UserDTO userdto) throws Exception;
	
	public UserDTO getUser(String userId) throws Exception;

	public List<UserDTO> getAllUsers();
	
	public List<UserDTO> searchUsers(List<String> userNames) throws Exception;


}

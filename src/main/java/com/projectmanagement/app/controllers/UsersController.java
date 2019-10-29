package com.projectmanagement.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.app.model.UserDTO;
import com.projectmanagement.app.service.UsersService;
import com.projectmanagement.app.entity.UsersVO;




@RestController
@RequestMapping("/users")
public class UsersController {


	
	@Autowired
	private	UsersService usersService;
	
	
	
	@CrossOrigin
	@GetMapping(value = "/allusers")
	public ResponseEntity<List<UserDTO>> getAllUsers()
	{
		List<UserDTO> userList=new ArrayList<UserDTO>();
		try
		{		
			userList=usersService.getAllUsers();
			
			return ResponseEntity.ok().body(userList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return ResponseEntity.badRequest().body(userList);
		
	}
	
	@CrossOrigin
	@PostMapping(value = "/searchusers")
	public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody List<String> userNames)
	{
		List<UserDTO> userList=new ArrayList<UserDTO>();
		try
		{		
			userList=usersService.searchUsers(userNames);
			
			return ResponseEntity.ok().body(userList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return ResponseEntity.badRequest().body(userList);
		
	}
	
	@CrossOrigin
	@PutMapping(value = "/user")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO)
	{
		String message="User is updated successfully";
		String badRequestMessage="Error has been occured while updating User";
		long userId=0L;		
		try
		{	
			userId=usersService.save(userDTO);
			 if(userId>0L)
			 {
				 userDTO.setMessage(message);			 
			 }
			 else
			 {
				 userDTO.setMessage(badRequestMessage);
				 
			 }
			 return ResponseEntity.ok().body(userDTO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			userDTO.setMessage(badRequestMessage);
			return ResponseEntity.badRequest().body(userDTO);
		}
				
	}
	
	@CrossOrigin
	@PostMapping(value = "/add")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO)
	{
		String message="User is added successfully";
		String badRequestMessage="Error has been occured while creating User";
		long userId=0L;		
		try
		{	
			userId=usersService.save(userDTO);
			 if(userId>0L)
			 {
				 userDTO.setMessage(message);
			 
			 }
			 else
			 {
				 userDTO.setMessage(badRequestMessage);
				
			 }
			 return ResponseEntity.ok().body(userDTO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			userDTO.setMessage(badRequestMessage);
			return ResponseEntity.badRequest().body(userDTO);
		}
				
	}
	

	@CrossOrigin
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId")String userId)
	{
		String message="User is added successfully";
		String badRequestMessage="Error has been occured while creating User";
		UserDTO userDto=new UserDTO();	
		try
		{	
			userDto=usersService.getUser(userId);
			 if(userDto.getUserId()!=null)
			 {
				
			 return ResponseEntity.ok().body(userDto);
			 }
			 else
			 {
				 
				return ResponseEntity.badRequest().body(userDto); 
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			userDto.setMessage(badRequestMessage);
			return ResponseEntity.badRequest().body(userDto);
		}
				
	}
	

}

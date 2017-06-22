/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monami.autotrek.controllers;

import com.monami.autotrek.service.UserServices;
import com.monami.autotrek.user.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prakasha
 */
@RestController
public class UserController {

	@Autowired
	private UserServices userService;
	
	@RequestMapping("/user")
		public List<User>  getAllUsers()
		{
                    
                    System.out.println("com.monami.autotrek.controllers.UserController.getAllUsers()");    
		return userService.getAllUser();
		     
		}
                
	@RequestMapping("/user/{id}")
	public User getUser(@PathVariable String id)
	{
		return userService.getUser(id);
	}
	@RequestMapping(method=RequestMethod.POST,value="/user")
	public void addUser(@RequestBody User user)
	{
		userService.addUser(user);
	}
	@RequestMapping(method=RequestMethod.PUT,value="/user/{id}")
	public void updateUser(@RequestBody User user,@PathVariable String id)
	{
		userService.updateUser(id,user);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/user/{id}")
	public void deleteUser(@PathVariable String id)
	{
		userService.deleteUser(id);
	}
}

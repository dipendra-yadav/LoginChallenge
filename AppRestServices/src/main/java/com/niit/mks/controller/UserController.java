package com.niit.mks.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.mks.dao.UserDAO;
import com.niit.mks.model.BaseError;
import com.niit.mks.model.User;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HttpSession session;
	

	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	// client is sending the data in JSON format. This method has to convert JSON to JAVA
	public ResponseEntity<?> insertUser(@RequestBody User user)
	{
		try {
		User newUser=userDAO.insert(user);
		return new ResponseEntity<User>(newUser,HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			BaseError error=new BaseError(2,"User cannot be inserted!!" +e.getMessage());
			return new ResponseEntity<BaseError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="/get/alluser", method=RequestMethod.GET)
	public ResponseEntity<?>  getAllUser()
	{
		List<User> users=userDAO.getAllUsers();	
		if(users.isEmpty())
		{
			BaseError error=new BaseError(1,"There is no users");
			return new ResponseEntity<BaseError>(error,HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
			
		}
	
		
	}
	@RequestMapping(value="/getuser" ,method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session)
	{
	
		User user=(User) session.getAttribute("user"); // only for authentication
		if(user==null)
		{
			BaseError error=new BaseError(3,"unauthorized user");
			return new ResponseEntity<BaseError>(error,HttpStatus.UNAUTHORIZED);
		}
		else
		{
			user=userDAO.getUserById(user.getId());
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user)
	{
		System.out.println("Login rest Hit***");
		User validUser=userDAO.login(user);
		if(validUser==null)
		{
			
			BaseError error=new BaseError(3,"Invalid Credentials..");
			return new ResponseEntity<BaseError>(error,HttpStatus.UNAUTHORIZED);
		}
		else
		{
			System.out.println("Data matched");
			session.setAttribute("user",validUser);
			
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logOut()
	{
		System.out.println("logout  Hit***");
		User user=(User) session.getAttribute("user");
		System.out.println(user+"**********");
		/*if(user==null)
		{
			BaseError error=new BaseError(3,"Please Login..");
			return new ResponseEntity<BaseError>(error,HttpStatus.UNAUTHORIZED);
		}
		else
		{
			user=userDAO.getUserById(user.getId());
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}*/
		
		
		if(user !=null)
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		else{
			BaseError error=new BaseError(3,"Please Login..");
		
			return new ResponseEntity<BaseError>(error,HttpStatus.UNAUTHORIZED);
		}
			
		
		
	}
	

}

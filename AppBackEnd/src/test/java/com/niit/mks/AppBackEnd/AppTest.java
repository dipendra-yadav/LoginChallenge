package com.niit.mks.AppBackEnd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.mks.dao.UserDAO;
import com.niit.mks.model.User;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static User user;
	
	@Autowired
	static UserDAO userDAO;
	
	@BeforeClass
	static public void initalize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.mks");
		context.refresh();
		
		user=context.getBean(User.class);
		
		userDAO=(UserDAO)context.getBean("userDAO");
		
	}
	
	@Test
	public void insertPositiveTestCase()
	{
		// positive testing for insert user
		user.setId(0);
		user.setFirstName("Sangram");
		user.setLastName("Hati");
		user.setEmailId("sangram@gmail.com");
		user.setPassword("abc");
		user.setUserName("sam");
		userDAO.insert(user);
		assertEquals("Record Inserted!!","Sangram",user.getFirstName());		
		
	}
		
	@Test
	public void getAllUserPositiveTestCase()
	{
		int usersCount=userDAO.getAllUsers().size();
		assertEquals("Fetched data!!",usersCount,userDAO.getAllUsers().size());
	}
	
	@Test
	public void getAllUsersNegativeTestCase()
	{
		assertFalse("Empty",userDAO.getAllUsers().isEmpty() );
		
	
	}
	
}

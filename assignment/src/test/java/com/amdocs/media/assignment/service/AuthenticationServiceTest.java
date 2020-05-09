package com.amdocs.media.assignment.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amdocs.media.assignement.AssignmentApplication;
import com.amdocs.media.assignement.dao.Profile;
import com.amdocs.media.assignement.service.UsersRepo;

@SpringBootTest(classes=AssignmentApplication.class)
public class AuthenticationServiceTest {

@Autowired
private UsersRepo ur;
	
	
	@Test
	void contextLoad()
	{
		
		assertEquals(Profile.class, ur.findByUserName("user").getClass());  
		
	}
}

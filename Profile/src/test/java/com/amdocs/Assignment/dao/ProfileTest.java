package com.amdocs.Assignment.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.amdocs.Assignment.dao.Profile;
import com.amdocs.Assignment.dao.Users;

@SpringBootTest
public class ProfileTest {

	
	@Test
	void contextload()
	{
		Profile p=new Profile();
		p.setAddress("asdwqd");
		p.setUserName("asdwqd");
		Users u=new Users();
		u.setUsername("das");
		u.setPassword("asdwqd");
		p.setUserid(u);
		p.setPhone("9712536789");	
		
		assertEquals("asdwqd", p.getUserName());
		
	}
	
}

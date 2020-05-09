package com.amdocs.Assignment.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.amdocs.Assignment.dao.Profile;
import com.amdocs.Assignment.dao.Users;
import com.amdocs.Assignment.service.ProfileServiceImpl;


@SpringBootTest
class ProfileServiceImplTest {

	@Autowired
	ProfileServiceImpl ps;

	@Test
	void contextLoads() {
		
		//System.out.println("running")
		ResponseEntity<?> p = ps.fetchByUserName("testuser");
		//System.out.println();
		assertEquals(Profile.class, p.getBody().getClass());
		
	}
	
	@Test
	void contextLoads1() {
		
		//System.out.println("running")
		Profile p=new Profile();
		p.setAddress("asdwqd");
		p.setUserName("asdwqd");
		Users u=new Users();
		u.setPassword("das");
		u.setPassword("asdwqd");
		p.setUserid(u);
		p.setPhone("9712536789");		
		ResponseEntity<?> ans = ps.save(p);
		//System.out.println();
		assertEquals(Profile.class, ans.getBody().getClass());
		
		
		
	}
	
	@Test
	void contextLoads2() {
		
		//System.out.println("running")
		Profile p=new Profile();
		Users u=new Users();
		assertEquals(true,  ps.Delete(p));
	}

}
package com.amdocs.Assignment.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.amdocs.Assignment.dao.Profile;
import com.amdocs.Assignment.dao.Users;
import com.amdocs.Assignment.service.ProfileServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
public class ProfileControllerTest {
	
	 @Autowired 
	 private MockMvc mvc;
	 
	 @MockBean 
	private ProfileServiceImpl psi;
	   
	   
	@Test
	public void contextLoads1() {
		Profile p=new Profile();
		p.setAddress("asdwqd");
		p.setUserName("asdwqd");
		Users u=new Users();
		u.setUsername("das");
		u.setPassword("asdwqd");
		p.setUserid(u);
		p.setPhone("9712536789");	
		try {
			
			ObjectMapper obj=new ObjectMapper();
			
		String json=obj.writeValueAsString(p);
			RequestBuilder request = MockMvcRequestBuilders
			        .post("/save")
			        .accept(MediaType.APPLICATION_JSON)
			        .content(json)
			        .characterEncoding("utf-8")
			        .contentType(MediaType.APPLICATION_JSON);

			 mvc.perform(request)
			        .andExpect(status().isOk()).andReturn();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assert(Profile.class, ans.getBody().getClass());
	
	}	
	
}

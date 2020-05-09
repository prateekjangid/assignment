package com.amdocs.Assignment.controller;


import static org.junit.Assert.assertEquals;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amdocs.Assignment.controller.ProfileConsumerController;
import com.amdocs.Assignment.dao.Profile;
import com.amdocs.Assignment.dao.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class ProfileConsumerControllerTest {
	
	
	@Autowired
	private ObjectMapper obj;
	
	@Autowired
	private ProfileConsumerController pcc;
	
	@Test
	void contextLoads() {
		Profile p=new Profile();
		p.setAddress("asdwqd");
		p.setUserName("asdwqd");
		Users u=new Users();
		u.setPassword("das");
		u.setPassword("asdwqd");
		p.setUserid(u);
		p.setPhone("9712536789");
		
		String value;
		try {
			value = obj.writeValueAsString(p);
			ConsumerRecord<Integer, String> cr=new ConsumerRecord<Integer, String>("profileupdate", 1, 1, 1,value );
			
			
			assertEquals(true, pcc.onMessage(cr).hasBody());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}

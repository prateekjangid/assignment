package com.amdocs.media.assignment.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amdocs.media.assignement.AssignmentApplication;
import com.amdocs.media.assignement.dao.AuthInfo;
import com.amdocs.media.assignement.dao.Profile;
import com.amdocs.media.assignement.service.KafkaProducer;


@SpringBootTest(classes=AssignmentApplication.class)
public class KafkaProducerTest {

	@Autowired
	private KafkaProducer kp;
	
	@Test
	void contextload()
	{
		
		Profile p=new Profile();
		p.setAddress("asdwqd");
		p.setUserName("asdwqd");
		AuthInfo u=new AuthInfo();
		u.setUsername("das");
		u.setPassword("asdwqd");
		p.setUserid(u);
		p.setPhone("9712536789");
		
		
		assertEquals(null, kp.sendProfile(p, 1));
		
	}
	
}

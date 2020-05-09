package com.amdocs.Assignment.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.amdocs.Assignment.dao.Profile;
import com.amdocs.Assignment.service.ProfileServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProfileConsumerController {

	@Autowired
	ObjectMapper obj;

	@Autowired
	private ProfileServiceImpl profileServiceImpl;
	
	
	@KafkaListener(topics = { "profileupdate" })
	public ResponseEntity<?> onMessage(ConsumerRecord<Integer, String> cr) {
		try {
			
			String type= cr.key().toString();
			Profile profile = obj.readValue(cr.value(), Profile.class);
			switch (type) {

			case "1":
				// delete
				
				boolean deleted = profileServiceImpl.Delete(profile);
				if (deleted) {
					return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please try again");

			case "2":
				
				// update
				Profile saveProfile = profileServiceImpl.update(profile);
				if (saveProfile == null) {
					return ResponseEntity.ok("please try again");
				}
				return ResponseEntity.ok(saveProfile);
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}

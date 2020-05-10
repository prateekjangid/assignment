package com.amdocs.Assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.Assignment.dao.Profile;
import com.amdocs.Assignment.service.ProfileServiceImpl;

@RestController
public class ProfileController {

	@Autowired
	private ProfileServiceImpl profileServiceImpl;

	@PostMapping("/save")
	public ResponseEntity<?> saveProfile(@RequestBody Profile entity) {

		if(entity==null||( 
				isnullNdBlank(entity.getUserName())
				|| isnullNdBlank(entity.getAddress())||
				isnullNdBlank(entity.getPassword())||
				isnullNdBlank(entity.getPhone())
				))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		}
		ResponseEntity<?> profile = profileServiceImpl.save(entity);

		if (profile == null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Username Exist");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(profile.getBody());
	}

	
	public boolean isnullNdBlank(String string)
	{
		
		if(string==null||string.trim().equalsIgnoreCase(""))
		{
			return true;
		}
		
		return false;
	}
	
}

package com.amdocs.media.assignement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.media.assignement.dao.AuthInfo;
import com.amdocs.media.assignement.dao.AuthenticationResponse;
import com.amdocs.media.assignement.dao.Profile;
import com.amdocs.media.assignement.service.AuthenticationService;
import com.amdocs.media.assignement.service.KafkaProducer;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private KafkaProducer kp;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	static Profile p=null; 	
	
	
	@PostMapping("/assignment")
	public ResponseEntity<?> allowLogin(@RequestBody AuthInfo user) throws Exception {
		try {
			if(user==null ||( 
					isnullNdBlank(user.getUsername())||
					isnullNdBlank(user.getPassword())
					))
			{
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please provide username and password.");
			}
		UserDetails userDetails=this.authenticationService.loadUserByUsername(user.getUsername());
		if(userDetails!=null &&( userDetails.getUsername().equals(user.getUsername()) 
				&& passwordEncoder.matches(user.getPassword(), userDetails.getPassword()))) {
			
			 p=this.authenticationService.loadData(userDetails.getUsername());
			
			AuthenticationResponse auth=new AuthenticationResponse();
			
			auth.setToken(userDetails.getUsername());
			
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please check Username and password or the only provide username and password.");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Could Not Authenticate User.");
		}
	}
	
	@PutMapping("/assignment/update")
	public ResponseEntity<?> update(@RequestBody Profile profile)
	{
		if(p==null)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login first.");
		}
		else {
			if(profile.getAddress()!=null)
			{
				p.setAddress(profile.getAddress());
			}
			if(profile.getPhone()!=null)
			{
				p.setPhone(profile.getPhone());
			}
			
			kp.sendProfile(p,2);
			
		}
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(p);
	}
	
	@DeleteMapping("/assignment/delete")
	public ResponseEntity<?> delete()
	{
		if(p==null)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login again");
		}
		else {
			
			kp.sendProfile(p,1);
			p=null;
		}
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
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

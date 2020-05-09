package com.amdocs.Assignment.service;

import org.springframework.http.ResponseEntity;

import com.amdocs.Assignment.dao.Profile;

public interface ProfileService {

	public ResponseEntity<?> fetchByUserName(String userName);
	public ResponseEntity<?>  save(Profile profile);
	public boolean Delete(Profile p); 
	public Profile update(Profile profile);
}

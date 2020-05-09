package com.amdocs.Assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amdocs.Assignment.dao.Profile;
import com.amdocs.Assignment.dao.ProfileRepo;
import com.amdocs.Assignment.dao.Users;
import com.amdocs.Assignment.dao.UsersRepo;

@Service
public class ProfileServiceImpl {

	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private UsersRepo ur;

	public ResponseEntity<?> fetchByUserName(String userName) {

		Profile profile = profileRepo.findByUserName(userName);

		if (profile == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No user exist for given username");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(profile);
	}

	public ResponseEntity<?> save(Profile p) {

		ResponseEntity<?> profile = fetchByUserName(p.getUserName());

		if (profile.getStatusCode() == HttpStatus.NO_CONTENT) {

			Users u = new Users();
			u.setPassword(p.getPassword());
			u.setUsername(p.getUserName());
			
			ur.save(u);
			p.setUserid(u);
			
			profileRepo.save(p);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(p);

		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username exist.");
	}

	public boolean Delete(Profile p) {

		// deleet pe

		Optional<Profile> profile = profileRepo.findById(p.getId());
		profile.ifPresent(theprofile -> profileRepo.delete(p));
		ur.delete(p.getUserid());
		Optional<Profile> deleted = profileRepo.findById(p.getId());
		return !deleted.isPresent();

	}

	public Profile update(Profile profile) {

//		
		Optional<Profile> p = profileRepo.findById(profile.getId());
//
		p.ifPresent(fetchedProfile -> {
			if (profile.getAddress() != null) {
				fetchedProfile.setAddress(profile.getAddress());
			}
			// fetchedProfile.setUserName(profile.getUserName());
			// fetchedProfile.setPassword(profile.getPassword());
			if (profile.getPhone() != null) {
				fetchedProfile.setPhone(profile.getPhone());
			}
			profileRepo.save(fetchedProfile);

		});

		return profile;
	}

}

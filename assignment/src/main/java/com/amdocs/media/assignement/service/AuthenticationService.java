package com.amdocs.media.assignement.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amdocs.media.assignement.dao.Profile;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UsersRepo userRepo;
	
	Profile p=null; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Profile user=userRepo.findByUserName(username);
		
		if(user!=null)
		{
			
			this.p=user;
			
			return new User(user.getUserName(), user.getPassword(),new ArrayList<>());	
		}
		
		return null;
	}
	
	public Profile loadData(String username) {	
	return this.p;	
	}

}

package com.amdocs.media.assignement.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.amdocs.media.assignement.dao.AuthInfo;
import com.amdocs.media.assignement.dao.Profile;

@Repository
public interface UsersRepo extends JpaRepository<Profile, Long>  {

	@Query(value="select * from profile p join users u on p.UID=u.UID where u.username=:userName",nativeQuery=true)
	public Profile findByUserName(String userName);

	@Query(value="select username,password from users where username=:userName",nativeQuery=true)
	public AuthInfo  findUserName(String userName);

	
}

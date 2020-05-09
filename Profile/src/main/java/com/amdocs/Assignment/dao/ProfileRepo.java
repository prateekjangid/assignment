package com.amdocs.Assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
	
	
	@Query(value="Select * from profile where username=:userName",nativeQuery=true)
	public Profile findByUserName(String userName);
	
	@Query(value="Select * from profile where id=:id",nativeQuery=true)
	public Profile findById(String id);

	@Query(value="delete from profile where id=:id",nativeQuery=true)
	public void delete(long id);

	
	
}

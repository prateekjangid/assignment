package com.amdocs.Assignment.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

	
	@Transactional
	@Query(value="Delete from users where username=:username",nativeQuery=true)
	public void DeletebyUsername(@Param("username")String username);
}

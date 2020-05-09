package com.amdocs.media.assignement.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	//@JsonProperty(access = Access.WRITE_ONLY)
	private long id;
	
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="address")
	private String address;
	@Column(name="phone")
	private String phone;
	
	@OneToOne
	@JoinColumn(name="uid")
	//@JsonProperty(access = Access.WRITE_ONLY)
	private AuthInfo userid;
}

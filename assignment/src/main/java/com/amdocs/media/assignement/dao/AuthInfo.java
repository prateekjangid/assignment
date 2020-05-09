package com.amdocs.media.assignement.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class AuthInfo {

	/*private String username;
	private String password;
	*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uid",nullable=false)
	private long uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(mappedBy="userid",cascade=CascadeType.ALL)
	@ToString.Exclude
	@JsonIgnore
	private Profile profile;
	
}

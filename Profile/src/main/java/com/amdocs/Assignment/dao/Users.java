package com.amdocs.Assignment.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users implements Serializable {
	
	private static final long serialVersionUID=123456789L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uid",nullable=false)
	private long uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(mappedBy="userid")
	@ToString.Exclude
	@JsonIgnore
	private Profile profile; 
	
}

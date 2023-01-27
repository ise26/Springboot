package com.entity.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	
	private String fullName;
	
	@ManyToMany
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name="user_id",referencedColumnName = "uid"),
			inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "rid")
			)
	private Set<Role> roles = new HashSet<>();
	
}

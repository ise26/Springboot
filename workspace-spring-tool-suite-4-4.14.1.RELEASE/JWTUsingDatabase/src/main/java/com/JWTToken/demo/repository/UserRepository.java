package com.JWTToken.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.JWTToken.demo.entity.User;

public interface UserRepository extends CrudRepository<User,Long> {

	public User findByEmail(String email);
}

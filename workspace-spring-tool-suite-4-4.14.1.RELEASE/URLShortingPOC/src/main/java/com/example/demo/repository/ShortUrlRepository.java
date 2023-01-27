package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Url;

@Repository
public interface ShortUrlRepository extends JpaRepository<Url, Integer>{

	/* public Url findByShortUrlLink(String url); */
	
	@Query(value="select * from Url where SHORT_LINK=?1" , nativeQuery=true)
	 public Url findByShortUrlLink(String url);
}

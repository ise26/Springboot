package com.urlshortner;

import java.net.URL;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlshortner.dto.UrlDto;

@Repository
public interface UrlRepo extends CrudRepository<UrlDto, Integer>{

	@Modifying
	@Query(value = "select url from url_dto where id =?1",nativeQuery = true)
	List geturl(String id);
	
	@Query(value = "select id from url_dto",nativeQuery = true)
	List getshortids();
	
	
	@Query(value = "select url from url_dto where id =?1",nativeQuery = true)
	public String getog(String id);
	
}

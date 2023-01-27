package com.resttemplate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resttemplate.Entity.LoggerData;
@Repository
public interface RestTemplateInterceptorRepo extends CrudRepository<LoggerData, Long>{

//	@Query (value = "update logger_data set response=?1 and responsetimestamp=?2  where id=?3 ",nativeQuery =true)
//	public void saveResponseData(String response,String timestamp,Long id);
//	
//	public Object findByRequesttimestamp(String request);
//	public List<LoggerData> findByRequest(String request);
}

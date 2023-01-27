package com.pincode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pincode.entity.Pincodes;

@Repository
public interface PincodeDataRepo extends CrudRepository<Pincodes, Integer>{

	@Query(value ="select * from pincodes where MASTER_PINCODES_NAME = ?1",nativeQuery = true)
	public List<Pincodes> findBymasterPincodesName(int pincode);
	
	@Query(value ="select * from pincodes where MASTER_PINCODES_NAME = ?1 AND CITY=?2",nativeQuery = true)
	public List<Pincodes> findBymasterPincodesNameAndcity(int pincode,String city);
	
	@Query(value ="select * from pincodes",nativeQuery = true)
	public List<Pincodes> getAllData();
	
	@Query(value = "select distinct state from pincodes",nativeQuery = true)
	public List<String> findallstate();
	
	@Query(value = "select * from pincodes where state =?1",nativeQuery = true)
	public List<Pincodes> findBystate(String state);
	
	@Query(value = "select distinct city from pincodes",nativeQuery = true)
	public List<String> findallcity();
	
	@Query(value = "select MASTER_PINCODES_NAME from pincodes where city=?1",nativeQuery = true)
	public List<Integer> findcity(String city);
	
	@Query(value = "select MASTER_PINCODES_NAME from pincodes where state=?1 AND city=?2",nativeQuery = true)
	public List<Integer> findmasterPincodesNameBystateAndcity(String state,String city);
	
}

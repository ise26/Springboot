package com.imageupload.repository;

import org.springframework.data.repository.CrudRepository;

import com.imageupload.entity.UploadImge;

public interface ImageRepository extends CrudRepository<UploadImge, Long>{



	
}

package com.panVerification.entity;

import lombok.Data;

@Data
public class Result<T> {
	 private Long response_type_id;
	 private Long response_status_id;
	 private String message;
	 private T data;
}

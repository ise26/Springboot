package com.resttemplate.Entity;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.http.client.ClientHttpResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggerData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String request;
	private String requesttimestamp;
	@Column(length = 4000)
	private String response;
	private String responsetimestamp;
	private String mobile_number;
		
}

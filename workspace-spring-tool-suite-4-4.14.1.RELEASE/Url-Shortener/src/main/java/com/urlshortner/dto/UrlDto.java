package com.urlshortner.dto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.google.common.hash.Hashing;

import io.netty.util.HashingStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tid;
	
	private String id;
	private String url;
	
}

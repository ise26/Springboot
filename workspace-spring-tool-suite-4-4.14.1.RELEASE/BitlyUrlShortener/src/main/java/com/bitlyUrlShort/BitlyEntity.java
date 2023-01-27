package com.bitlyUrlShort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitlyEntity {

	private String accessToken;
	private String originalUrl;
}

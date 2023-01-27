package com.bitlyUrlShort.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {


	private String group_guid;
	private String domain;
	private String long_url;
}

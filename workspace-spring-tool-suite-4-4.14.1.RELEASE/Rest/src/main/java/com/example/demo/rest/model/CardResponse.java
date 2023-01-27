package com.example.demo.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class CardResponse {
	
	    @JsonFormat(shape = Shape.STRING, pattern = "dd-mm-yyyy hh::mm:ss")
	    Date timestamp;
	    String message;
	    String details;
		public Date getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getDetails() {
			return details;
		}
		public void setDetails(String details) {
			this.details = details;
		}
		public CardResponse(Date timestamp, String message, String details) {
			super();
			this.timestamp = timestamp;
			this.message = message;
			this.details = details;
		}
	
}

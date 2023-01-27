package com.panVerification;

import lombok.Data;

@Data
public class PanDetails {

 	private String pan_number ;
	private int purpose;
	private String initiator_id;
	private String purpose_desc;
}

package com.promineotech.cakes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Icings {	
	private Long icing_pk; 
	private String icing_id; 
	private String icing_name; 
	private String icing_ingredients; 
	
// Added getter - will take out the PK	
		@JsonIgnore
		public Long getIcing_pk() {
			return icing_pk;
		}
}


/*
CREATE TABLE icings (
	icing_pk int AUTO_INCREMENT NOT NULL, 
	icing_id varchar(20) NOT NULL,
	icing_name varchar(30) NOT NULL, 
	icing_ingredients text(200) NOT NULL, 
	PRIMARY KEY (icing_pk),
	UNIQUE KEY (icing_id, icing_name)
);
*/
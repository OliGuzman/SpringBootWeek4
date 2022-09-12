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
public class Fillings {
	private Long filling_pk; 
	private String filling_id; 
	private String filling_name; 
	private String filling_ingredients; 
	
// Added getter - will take out the PK	
		@JsonIgnore
		public Long getFilling_pk() {
			return filling_pk;
		}
}



/*
CREATE TABLE fillings (
	filling_pk int AUTO_INCREMENT NOT NULL,  
	filling_id varchar(20) NOT NULL,
	filling_name varchar(30) NOT NULL, 
	filling_ingredients text(200) NOT NULL, 
	PRIMARY KEY (filling_pk),
	UNIQUE KEY (filling_id)
);

*/
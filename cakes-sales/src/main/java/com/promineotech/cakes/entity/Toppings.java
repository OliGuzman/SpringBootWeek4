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
public class Toppings {	
	private Long topping_pk; 
	private String topping_id; 
	private String topping_name; 
	private String topping_ingredients; 
	
// Added getter - will take out the PK	
		@JsonIgnore
		public Long getTopping_pk() {
			return topping_pk;
		}
}


/*
CREATE TABLE toppings (
	topping_pk int AUTO_INCREMENT NOT NULL, 
  	topping_id varchar(20) NOT NULL,
  	topping_name varchar(30) NOT NULL,
  	topping_ingredients text(200) NOT NULL,
  	PRIMARY KEY (topping_pk),
  	UNIQUE KEY (topping_id)
);
*/
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
public class Customers {
	private Long customer_pk; 	
	private String customer_id;
	private String first_name; 
	private String last_name;
	private String phone_number; 
	
	@JsonIgnore
	public String customer_id() {
		return customer_id;
	}
}


/*
CREATE TABLE customers (
	customer_pk int AUTO_INCREMENT NOT NULL, 
  	customer_id varchar(20) NOT NULL,
  	first_name varchar(30) NOT NULL,
  	last_name varchar(30) NOT NULL,
  	phone_number varchar(20) NOT NULL,
  	PRIMARY KEY (customer_pk)  	
);

*/
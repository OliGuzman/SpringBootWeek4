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
public class Packages {	
	private Long package_pk; 
	private String package_id; 
	private String package_name; 
	private String package_contents; 
	
// Added getter - will take out the PK	
		@JsonIgnore
		public Long getPackagePK() {
			return package_pk;
		}
}


/*
CREATE TABLE packages(
		package_pk int AUTO_INCREMENT NOT NULL, 
		package_id varchar(20) NOT NULL, 
		package_name varchar(30) NOT NULL, 
		package_contents text(200) NOT NULL,
		PRIMARY KEY (package_pk),
		UNIQUE KEY (package_id)
);
*/
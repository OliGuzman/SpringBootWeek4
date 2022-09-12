package com.promineotech.cakes.entity;


public enum CakesTypes {
	BUTTER, CARROT, RED_VELVET
}










/*
 * Added cake_id Enum
 *
*CREATE TABLE cake (
CREATE TABLE cakes (
	cake_pk int AUTO_INCREMENT NOT NULL, 
	cake_id enum ('BUTTER', 'CARROT', 'RED_VELVET') NOT NULL,
  	cake_flavor varchar(20) NOT NULL,
  	cake_size int NOT NULL,
  	num_tiers int NOT NULL,
  	cake_shape varchar(20) NOT NULL,
  	PRIMARY KEY (cake_pk),
	UNIQUE KEY (cake_id, cake_flavor, cake_size, num_tiers, cake_shape)
 );

*/
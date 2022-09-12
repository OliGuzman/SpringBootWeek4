package com.promineotech.cakes.entity;

import java.util.Comparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cakes implements Comparable<Cakes>{
	private Long cake_pk; 
	private CakesTypes cake_id;                   //cake_id is actually the cake type i.e. butter, carrot, red velvet
	private String cake_flavor; 
	private int cake_size; 
	private int num_tiers; 
	private String cake_shape;	
	
/**
 * Tells Jackson not to populate the PK value coming back. You don't want guessable values coming back.
 * Added a getter (getCakePK).
 * 
 * Added @ JsonIgnore - when Jackson is serializing the object into JSON, it'll ignore the cakePK
 * When it's going the other way, if it finds a PK, it'll populate it in the object because we haven't
 * put JsonIgnore on the setter, only on the getter.  
 * 
 */
	
//	@JsonIgnore
//	public Long getCake_pk() {
//		return cake_pk;
//	}
	
	@JsonIgnore
	public CakesTypes cake_id() {
		return cake_id;
	}

/**
 *  You don't want to rely on the default order coming out of the database (possibly order it was put in, which is unknown) 
 *  It's best to apply a sort of the response to get it in the order you want. 
 *  Used Comparator to do sorting of the returned data. It compares the current object "this" to the one that gets
 *  passed into the method "that"(returns an int number - if this is less than that it returns less than 0, 
 *  if this is greater than that it returns greater than 0, if they're equal it returns 0).  1st it compares 1st on Cake_pk
 *  & if they're the same, then it compares on to Cake_id, then CakeFlavor (alpha sort) & then the number of tiers etc. 	
 *  
 */
	
//@Override
public int compareTo(Cakes that) {
	// @formatter:off
	return Comparator
			.comparing(Cakes::getCake_pk)
			.thenComparing(Cakes::getCake_id)
			.thenComparing(Cakes::getCake_flavor)
			.thenComparing(Cakes::getCake_size)
			.thenComparing(Cakes::getNum_tiers)
			.thenComparing(Cakes::getCake_shape)
			.compare(this, that);
	// @formatter:on
	}
}

/*
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
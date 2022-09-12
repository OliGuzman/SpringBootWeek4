package com.promineotech.cakes.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	private Long orderPK; 
	private Customers customers; 
	private Cakes cakes; 
	private Toppings toppings; 
	private Icings icings; 
	private Fillings fillings; 
	private List<Packages> packages; 
	
// Added getter - will take out the PK	
	@JsonIgnore
	public Long getOrderPK() {
		return orderPK;
	}

}


/*
CREATE TABLE orders (
	order_pk int AUTO_INCREMENT NOT NULL, 
	customer_fk int NOT NULL, 
	cake_fk int NOT NULL, 
	topping_fk int NOT NULL, 
	icing_fk int NOT NULL, 
	filling_fk int NOT NULL,
	PRIMARY KEY (order_pk),
	FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE, 
	FOREIGN KEY (cake_fk) REFERENCES cakes (cake_pk) ON DELETE CASCADE, 
	FOREIGN KEY (topping_fk) REFERENCES toppings (topping_pk) ON DELETE CASCADE,
	FOREIGN KEY (icing_fk) REFERENCES icings (icing_pk) ON DELETE CASCADE, 
	FOREIGN KEY (filling_fk) REFERENCES fillings (filling_pk) ON DELETE CASCADE	
); 
*/

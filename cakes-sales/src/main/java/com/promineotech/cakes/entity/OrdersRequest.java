package com.promineotech.cakes.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Adding @ Data - populates getters & setters
 * Jackson has to have setters to populate this object
 * 
 * Added Bean validation 
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")	
	private String customers; 
	
	@NotNull
	private CakesTypes types; 
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")	
	private String flavors;
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")	
	private String shapes; 
		
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")	
	private String toppings; 
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")	
	private String icings; 
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")	
	private String fillings; 
	
	@NotNull
	private List<@NotNull @Length(max = 30) @Pattern(regexp = "[\\w\\s]*") String> packages; 
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

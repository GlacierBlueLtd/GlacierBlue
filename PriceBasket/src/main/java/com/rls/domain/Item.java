package com.rls.domain;

import java.math.BigDecimal;

/**
 * @author Robin
 * 
 * Models basket item interface
 *
 */
public interface Item {
	
	public String getName();
	public BigDecimal getPrice();
	public QuantityType getQtyType();

}

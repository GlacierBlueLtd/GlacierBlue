package com.rls.domain;

import java.math.BigDecimal;

/**
 * @author Robin
 * 
 * Implementation of basket item
 *
 */

public class ItemImpl implements Item  {

	protected final String name;
	protected final BigDecimal price;
	protected final QuantityType quantityType;
	
	public ItemImpl(String name, BigDecimal price, QuantityType quantityType) {
		super();
		this.name = name;
		this.price = price;
		this.quantityType = quantityType;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public QuantityType getQtyType() {
		return quantityType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantityType == null) ? 0 : quantityType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemImpl other = (ItemImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantityType != other.quantityType)
			return false;
		return true;
	}

}

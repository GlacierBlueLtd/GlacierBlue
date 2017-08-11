package com.rls.service;

import java.math.BigDecimal;
import java.util.Collection;

import com.rls.domain.Item;
import com.rls.domain.rule.SpecialOffer;

/**
 * @author Robin
 * 
 *  PriceBasketService interface
 *
 */

public interface PriceBasketService {
	
	public BigDecimal priceBasket(Collection<Item> basket) throws Exception;
	
	public void addSpecialOffer(SpecialOffer<Collection<Item>> offer);

}

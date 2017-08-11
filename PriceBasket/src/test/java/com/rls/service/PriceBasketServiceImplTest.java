package com.rls.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.rls.domain.Item;
import com.rls.domain.ItemImpl;
import com.rls.domain.QuantityType;
import com.rls.domain.rule.DiscountProduct;
import com.rls.domain.rule.HalfPriceTargetProduct;
import com.rls.domain.rule.SpecialOffer;

/**
 * @author Robin
 * 
 *  PriceBasketServiceImplTest tests the service
 *
 */

public class PriceBasketServiceImplTest {

	private SpecialOffer<Collection<Item>> discountProduct =  new DiscountProduct("Apples", new BigDecimal("10.00"));
	private SpecialOffer<Collection<Item>> halfPriceTargetProduct =  new HalfPriceTargetProduct("Soup", "Bread");
	
	@Test
	public void testPriceBasketServiceNoOffers() throws Exception {
		 List<Item> basket = Arrays.asList(new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		                                   new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE));
		
		PriceBasketService priceBasketService = new PriceBasketServiceImpl();
		priceBasketService.addSpecialOffer(discountProduct); 
		priceBasketService.addSpecialOffer(halfPriceTargetProduct); 	
		
		assertThat(priceBasketService.priceBasket(basket), equalTo(new BigDecimal("2.10")));
	}
	

	@Test
	public void testPriceBasketServiceApplesOffer() throws Exception {
		 List<Item> basket = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN), 
		                                   new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		                                   new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE),
		                                   new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG));

			PriceBasketService priceBasketService = new PriceBasketServiceImpl();
			priceBasketService.addSpecialOffer(discountProduct); 
			priceBasketService.addSpecialOffer(halfPriceTargetProduct); 	
			
			assertThat(priceBasketService.priceBasket(basket), equalTo(new BigDecimal("3.65")));
	}
	
	
	@Test
	public void testPriceBasketServiceApplesAndBreadOffer() throws Exception {
		 List<Item> basket = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN),
				                           new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN),
		                                   new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		                                   new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE),
		                                   new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG));

			PriceBasketService priceBasketService = new PriceBasketServiceImpl();
			priceBasketService.addSpecialOffer(discountProduct); 
			priceBasketService.addSpecialOffer(halfPriceTargetProduct); 	
			
			assertThat(priceBasketService.priceBasket(basket), equalTo(new BigDecimal("3.90")));
	}
}

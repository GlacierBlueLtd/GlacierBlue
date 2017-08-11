package com.rls.domain.rule;

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

public class HalfPriceTargetProductTest {

	private SpecialOffer<Collection<Item>> halfPriceTargetProduct =  new HalfPriceTargetProduct("Soup", "Bread");
	
	@Test
	public void testDiscountForNoTargetSoup() {
		 List<Item> basket = Arrays.asList(new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		                                   new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE));
		SpecialOfferResult result = halfPriceTargetProduct.applyOffer(basket);
		assertThat(result.getDiscount(), equalTo(BigDecimal.ZERO));
		assertThat(result.getDiscountLog(), equalTo(""));
	}
	

	@Test
	public void testDiscountForOneTargetSoup() {
		 List<Item> basket = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN),
		                                   new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		                                   new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE),
		                                   new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG));
		SpecialOfferResult result = halfPriceTargetProduct.applyOffer(basket);
		assertThat(result.getDiscount(), equalTo(BigDecimal.ZERO));
		assertThat(result.getDiscountLog(), equalTo(""));
	}
	

	@Test
	public void testDiscountForTwoTargetSoup() {
		 List<Item> basket = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN),
				                           new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN), 
		                                   new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		                                   new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE),
		                                   new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG));
		SpecialOfferResult result = halfPriceTargetProduct.applyOffer(basket);
		assertThat(result.getDiscount(), equalTo(new BigDecimal("0.40")));
		assertThat(result.getDiscountLog(), equalTo("Bread 50% off: -40p"));
	}

}

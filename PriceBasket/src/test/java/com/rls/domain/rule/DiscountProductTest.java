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

/**
 * @author Robin
 * 
 *  DiscountProductTest test the discount offer 
 *
 */

public class DiscountProductTest {

	private SpecialOffer<Collection<Item>> discountProduct =  new DiscountProduct("Apples", new BigDecimal("10.00"));
	
	@Test
	public void testDiscountForNoApple() {
		 List<Item> basket = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN), 
		            new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		            new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE));
		SpecialOfferResult result = discountProduct.applyOffer(basket);
		assertThat(result.getDiscount(), equalTo(BigDecimal.ZERO));
		assertThat(result.getDiscountLog(), equalTo(""));
	}
	

	@Test
	public void testDiscountForTwoApples() {
		 List<Item> basket = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN), 
		            new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		            new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE),
		            new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG),
		 			new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG));
		SpecialOfferResult result = discountProduct.applyOffer(basket);
		assertThat(result.getDiscount(), equalTo(new BigDecimal("0.20")));
		assertThat(result.getDiscountLog().split("\n").length, equalTo(2));
	}
	

	@Test
	public void testDiscountForOneApple() {
		 List<Item> basket = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN), 
		            new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
		            new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE),
		            new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG));
		SpecialOfferResult result = discountProduct.applyOffer(basket);
		assertThat(result.getDiscount(), equalTo(new BigDecimal("0.10")));
		assertThat(result.getDiscountLog(), equalTo("Apples 10% off: -10p"));
	}

}

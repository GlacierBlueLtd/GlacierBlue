package com.rls.app;

import java.util.Arrays;
import org.junit.Test;
import com.rls.domain.exception.NoItemFoundException;

/**
 * @author Robin
 * 
 *  PriceBasketTest test the main application
 *
 */

public class PriceBasketTest {

	@Test
	public void testPricingBasketDoesntThrowException() throws Exception {
		PriceBasket priceBasket = new PriceBasket();
		priceBasket.priceTheBasket(Arrays.asList("Soup","Bread","Milk","Apples"));
	}
	
	@Test(expected = NoItemFoundException.class)
	public void testPricingBasketThrowsException() throws Exception {
		PriceBasket priceBasket = new PriceBasket();
		priceBasket.priceTheBasket(Arrays.asList("Sup","Bread","Milk","Apples"));
	}
}

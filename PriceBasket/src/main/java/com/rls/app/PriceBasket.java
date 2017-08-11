package com.rls.app;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.rls.domain.Item;
import com.rls.domain.ItemImpl;
import com.rls.domain.QuantityType;
import com.rls.domain.exception.NoItemFoundException;
import com.rls.domain.rule.DiscountProduct;
import com.rls.domain.rule.HalfPriceTargetProduct;
import com.rls.domain.rule.SpecialOffer;
import com.rls.service.PriceBasketService;
import com.rls.service.PriceBasketServiceImpl;

/**
 * @author Robin
 * 
 * A command line application to price a basket of items and apply discounts
 *
 */

public class PriceBasket {

	private final static Logger logger = Logger.getLogger(PriceBasket.class);
	
	private final List<Item> items = Arrays.asList(new ItemImpl("Soup", new BigDecimal("0.65"), QuantityType.TIN), 
            new ItemImpl("Bread", new BigDecimal("0.80"), QuantityType.LOAF),
            new ItemImpl("Milk", new BigDecimal("1.30"), QuantityType.BOTTLE),
            new ItemImpl("Apples", new BigDecimal("1.00"), QuantityType.BAG));

	// NB could read and populate a map from a JSON file as future enhancement 
	private final Map<String, Item> priceDictionary = items.stream().collect(Collectors.toMap (Item::getName, item->item));

	private SpecialOffer<Collection<Item>> discountProduct =  new DiscountProduct("Apples", new BigDecimal("10.00"));
	private SpecialOffer<Collection<Item>> halfPriceTargetProduct =  new HalfPriceTargetProduct("Soup", "Bread");
	
	private PriceBasketService priceBasketService;
	
	public PriceBasket() {
		priceBasketService = new PriceBasketServiceImpl();
		priceBasketService.addSpecialOffer(discountProduct); 
		priceBasketService.addSpecialOffer(halfPriceTargetProduct); 	
	}
	
	public void priceTheBasket(List<String> basket) throws Exception {
		try {
			List<String> invalidItems = validateItems(basket);
			if (invalidItems.size() == 0) {
				priceBasketService.priceBasket(createBasket(basket));
			}
			else {
				throw new NoItemFoundException(invalidItems.stream().collect(Collectors.joining(",")));
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage());
    		throw e;
		}
	}
	
	private Optional<Item> price(String name) {
		return Optional.of(priceDictionary.get(name));
	}

	private List<Item> createBasket(Collection<String> items) {
		return items.stream().map(i->priceDictionary.get(i)).collect(Collectors.toList());
	}
	
	private List<String> validateItems(Collection<String> items) {
		return items.stream().filter(i->!priceDictionary.containsKey(i)).collect(Collectors.toList());
	}
	
	public static void main(String[] args) throws Exception {
		PriceBasket priceBasket = new PriceBasket();
		priceBasket.priceTheBasket((Arrays.asList(args)));
	}

}

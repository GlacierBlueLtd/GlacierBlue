package com.rls.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rls.domain.Item;
import com.rls.domain.ItemImpl;
import com.rls.domain.exception.NoItemFoundException;
import com.rls.domain.rule.SpecialOffer;
import com.rls.domain.rule.SpecialOfferResult;
import com.rls.domain.QuantityType;

/**
 * @author Robin
 * 
 * A service to price a basket and apply discounts
 *
 */
public class PriceBasketServiceImpl implements PriceBasketService {
	
	private final List<SpecialOffer<Collection<Item>>> specialOffers = new ArrayList<>();
	
	private static final String NO_OFFERS = "(No offers available)";
	private static final String SUB_TOTAL = "Subtotal: £%s";
	private static final String TOTAL_PRICE = "Total price: £%s";
	
	public PriceBasketServiceImpl() {
		super();
	}

	private BigDecimal subTotal(Collection<Item> basket) throws Exception {
		return basket.stream()
				.map(Item::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public BigDecimal priceBasket(Collection<Item> basket) throws Exception {
		
		// sum total cost of items in the basket
		BigDecimal subTotal = subTotal(basket);
		
		// now get all the discounts that are more than zero
		List<SpecialOfferResult> results =  specialOffers.stream()
	              .map(offer->offer.applyOffer(basket))
				  .filter(offer->offer.getDiscount().compareTo(BigDecimal.ZERO) > 0)
				  .collect(Collectors.toList());
		
		// now subtract the discounts from the total basket
		if (results.size() > 0) {
			System.out.println(String.format(SUB_TOTAL, subTotal));
			subTotal = results.stream()
								.map(offer->{ System.out.println(offer.getDiscountLog());
								              return offer.getDiscount();})
								.reduce(subTotal, BigDecimal::subtract);
	
			System.out.println(String.format(TOTAL_PRICE, subTotal));
		}
		else {
			System.out.println(String.format(SUB_TOTAL, subTotal));
			System.out.println(String.format(NO_OFFERS));
			System.out.println(String.format(TOTAL_PRICE, subTotal));
		}
		
		return subTotal;
	}

	public void addSpecialOffer(SpecialOffer<Collection<Item>> offer) {
		specialOffers.add(offer);
	}

}

package com.rls.domain.rule;

import java.math.BigDecimal;
import java.util.Collection;

import com.rls.domain.Item;

/**
 * @author Robin
 * 
 *  HalfPriceTargetProduct test the half price offer on n items of another product
 *
 */

public class HalfPriceTargetProduct extends SpecialOfferImpl implements SpecialOffer<Collection<Item>> {
	 
	private final BigDecimal HALF = new BigDecimal("2.00");
	private final String name;
	private final String targetName;

	public HalfPriceTargetProduct(String name, String targetName) {
		super();
		this.name = name;
		this.targetName = targetName;
	}
	
	@Override
	public SpecialOfferResult applyOffer(Collection<Item> t) {
	    discountLog.clear(); 
		BigDecimal discount = BigDecimal.ZERO;	
		long count = t.stream()
		            .filter(item->item.getName().equals(name)).count();
			
		 if (count >= 2) {
			 discount =  t.stream()
	            .filter(item->item.getName().equals(targetName))
				.map(Item::getPrice)
				.map(price->price.divide(HALF, BigDecimal.ROUND_HALF_UP))
				.reduce(BigDecimal.ZERO, this::logDiscount);
		 }
		 return new SpecialOfferResult(discount, discountLog);
	}
	
	protected BigDecimal logDiscount(BigDecimal total, BigDecimal amount) {
		total =  total.add(amount);
		discountLog.add(String.format("%s %s%s off: -%sp", targetName, 50, "%", format.format(amount.multiply(HUNDRED))));
		return total;
	}
}


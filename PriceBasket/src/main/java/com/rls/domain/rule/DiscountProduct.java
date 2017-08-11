package com.rls.domain.rule;

import java.math.BigDecimal;
import java.util.Collection;
import com.rls.domain.Item;

/**
 * @author Robin
 * 
 *  Discounts all type items by a %
 *
 */

public final class DiscountProduct extends SpecialOfferImpl implements SpecialOffer<Collection<Item>> {

	private final BigDecimal percentDiscount;
	private final String name;
	
	public DiscountProduct(String name, BigDecimal percentDiscount) {
		super();
		this.name = name;
		this.percentDiscount = percentDiscount;
	}

	@Override
	public SpecialOfferResult applyOffer(Collection<Item> t) {
		discountLog.clear();
		BigDecimal discount = t.stream()
	            .filter(item->item.getName().equals(name))
				.map(Item::getPrice)
				.map(price->price.multiply(percentDiscount).divide(HUNDRED))
		        .reduce(BigDecimal.ZERO, this::logDiscount);
		return new SpecialOfferResult(discount, discountLog);
	}
	
	protected BigDecimal logDiscount(BigDecimal total, BigDecimal amount) {
		total =  total.add(amount);
		discountLog.add(String.format("%s %s%s off: -%sp", name, format.format(percentDiscount), "%", format.format(amount.multiply(HUNDRED))));
		return total;
	}
	
}

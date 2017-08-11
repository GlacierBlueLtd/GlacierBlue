package com.rls.domain.rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Robin
 * 
 *  SpecialOffer result class to store results
 *
 */

public class SpecialOfferResult {

	private final BigDecimal discount;
	private final List<String> discountLog;
	
	public SpecialOfferResult(BigDecimal discount, List<String> discountLog) {
		super();
		this.discount = discount;
		this.discountLog = discountLog;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public String getDiscountLog() {
		return discountLog.stream().
				collect(Collectors.joining("\n")).toString();
	}
}

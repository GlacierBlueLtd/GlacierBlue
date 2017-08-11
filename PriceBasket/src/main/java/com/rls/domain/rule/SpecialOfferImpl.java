package com.rls.domain.rule;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robin
 * 
 *  SpecialOffer base class common fields
 *
 */
public abstract class SpecialOfferImpl {

	protected final DecimalFormat format = new DecimalFormat("###");
	protected final BigDecimal HUNDRED = new BigDecimal("100.00");
	public final List<String> discountLog = new ArrayList<>();

	public SpecialOfferImpl() {
		super();
	}

}
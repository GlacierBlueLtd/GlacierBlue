package com.rls.domain.rule;

/**
 * @author Robin
 * 
 *  SpecialOffer interface
 *
 */

public interface SpecialOffer<T> {
	SpecialOfferResult applyOffer(T t);
}

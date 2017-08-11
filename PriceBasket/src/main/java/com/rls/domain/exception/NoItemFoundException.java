package com.rls.domain.exception;

/**
 * @author Robin
 * 
 *  Thrown when no item is in the map of items
 *
 */

public class NoItemFoundException extends Exception {

	public NoItemFoundException(String e) {
		super(e);
	}

}

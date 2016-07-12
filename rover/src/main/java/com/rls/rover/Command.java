package com.rls.rover;

/**
 * @author Robin Stone
 * 
 *  Command functional interface 
 *
 */
public interface Command<C> {
	
	public void execute(C command);

}

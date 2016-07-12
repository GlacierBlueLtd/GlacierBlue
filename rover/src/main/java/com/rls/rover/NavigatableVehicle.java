package com.rls.rover;

/**
* @author Robin Stone
* 
*  Models vehicle
*
*/
public interface NavigatableVehicle {
	
	public final String LEFT = "L";
	public final String RIGHT = "R";
	public final String MOVE = "M";
	
	public void rotateLeft();
	public void rotateRight();
	public void move();

}

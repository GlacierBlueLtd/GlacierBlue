package com.rls.rover;

/**
* @author Robin Stone
* 
*  Models a rover helper interface
*
*/
public interface RoverCommand {
	
	public Location rotateLeft(Location l) throws Exception;
	public Location rotateRight(Location l) throws Exception;
	public Location move(Location l) throws Exception;

}

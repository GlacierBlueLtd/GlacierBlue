package com.rls.rover;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
* @author Robin Stone
* 
*  Main class of how it could be used in sending messages to NASA rovers
*
*/
public class NASAControl 
{
	
	private static Queue<NASARover> rovers;
	private static String message[] = {"5 5", 
			                           "1 2 N",
			                           "LMLMLMLMM",
			                           "3 3 E", 
			                           "MMRMMRMRRM"};
	
			
    public static void main( String[] args ) {
    	NASAControl control = new NASAControl();
    	control.signalRovers();
    	control.printRoverLocations();
    }

	public NASAControl() {
		super();
		rovers = new ConcurrentLinkedQueue<NASARover>();
		rovers.add(new NASARover(1));
		rovers.add(new NASARover(2));
	}
    
	public void signalRovers() {
		rovers.stream().forEach(rover->rover.onMessage(message));
	}
	
	public void printRoverLocations() {
		rovers.stream().map(NASARover::getCurrentLocation).map(Location::toString).forEach(System.out::println);
	}
    
    
}

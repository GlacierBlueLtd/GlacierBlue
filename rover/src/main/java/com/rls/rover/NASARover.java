package com.rls.rover;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robin Stone
 * 
 * Models a rover 
 *
 */
public class NASARover implements NavigatableVehicle, CommandMessage {
	
	private Integer roverId;
	private Location currentLocation;
	private Location destinationLocation;
	
	private RoverNavigator navigator; 
	
	private Map<String, Command<NavigatableVehicle>> commandInterpreter = new HashMap<String, Command<NavigatableVehicle>>();
	
	public NASARover(Integer roverId) {
		super();
		this.roverId = roverId;
		commandInterpreter.put(NavigatableVehicle.LEFT, c->c.rotateLeft());
		commandInterpreter.put(NavigatableVehicle.RIGHT, c->c.rotateRight());
		commandInterpreter.put(NavigatableVehicle.MOVE, c->c.move());
	}
	
	
	public Location getCurrentLocation() {
		return currentLocation;
	}


	public Location getDestinationLocation() {
		return destinationLocation;
	}

	public void rotateLeft() {
		currentLocation = navigator.rotateLeft(currentLocation);	
	}

	public void rotateRight() {
		currentLocation = navigator.rotateRight(currentLocation);
	}

	public void move() {
		currentLocation = navigator.move(currentLocation);
	}
	
	private void doCommands(String message) {
		message.chars().mapToObj(i -> "" + (char)i).forEach(command->excuteCommand(command, this));  
	}
	
	private void excuteCommand(String command, NavigatableVehicle nv)  {
		commandInterpreter.get(command).execute(nv);
	}
	

	@Override
	public void onMessage(String[] data) {
		destinationLocation = new Location(data[0]);
		navigator = new RoverNavigator(destinationLocation);
		currentLocation = new Location(data[roverId == 1 ? roverId : roverId + 1]);
		doCommands(data[roverId == 1 ? roverId + 1 : roverId + 2]);
	}

}

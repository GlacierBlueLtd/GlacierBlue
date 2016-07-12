package com.rls.rover;

/**
* @author Robin Stone
* 
*  Models a rover helper to navigate to next state
*
*/

public class RoverNavigator implements RoverCommand {
	
	private Location origin = new Location(0, 0, Heading.NONE);
	private Location boundary ;
	
	public RoverNavigator(Location boundary) {
		this.boundary = boundary;
	}

	public Location rotateLeft(Location l)  {
		if (l.getHeading().equals(Heading.N)) {
			return new Location(l.x, l.y, Heading.W);
		}
		else if (l.getHeading().equals(Heading.E)) {
			return new Location(l.x, l.y, Heading.N);
		}
		else if (l.getHeading().equals(Heading.S)) {
			return new Location(l.x, l.y, Heading.E);
		}
		else if (l.getHeading().equals(Heading.W)) {
			return new Location(l.x, l.y, Heading.S);
		}
		else return l;
		
	}

	public Location rotateRight(Location l)  {
		if (l.getHeading().equals(Heading.N)) {
			return new Location(l.x, l.y, Heading.E);
		}
		else if (l.getHeading().equals(Heading.E)) {
			return new Location(l.x, l.y, Heading.S);
		}
		else if (l.getHeading().equals(Heading.S)) {
			return new Location(l.x, l.y, Heading.W);
		}
		else if (l.getHeading().equals(Heading.W)) {
			return new Location(l.x, l.y, Heading.N);
		}
		else return l;
		
	}

	public Location move(Location l)  {
		if (l.heading.equals(Heading.N)) {
			if (l.y < boundary.y) {
				return new Location(l.x, l.y + 1, l.heading);
			}
			return l;
		}
		else if (l.heading.equals(Heading.S)) {
			if (l.y > origin.y) {
				return new Location(l.x, l.y - 1, l.heading);
			}
			return l;
		}
		else if (l.heading.equals(Heading.E)) {
			if (l.x < boundary.x) {
				return new Location(l.x + 1, l.y, l.heading);
			}
			return l;
		}
		else if (l.heading.equals(Heading.W)) {
			if (l.x > origin.x) {
				return new Location(l.x - 1, l.y, l.heading);
			}
			return l;
		}
		else return l;
		
	}

	
	
	
	
}
